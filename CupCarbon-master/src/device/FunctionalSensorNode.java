package device;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import battery.Battery;
import buildings.BuildingList;
import project.Project;
import sensorunit.DirectionalSensorUnit;
import sensorunit.FunctionalSensorUnit;
import sensorunit.SensorUnit;
import utilities.MapCalc;

/**
 * @author Yiwei Yao
 */

public class FunctionalSensorNode extends SensorNode {

	/**
	 * Constructor 1 
	 * Instanciate the sensor unit 
	 * Instanciate the battery
	 */
	public FunctionalSensorNode() {
		super();
		sensorUnit = new DirectionalSensorUnit(this.longitude, this.latitude, this.elevation, this);		
	}
	
	/**
	 * Constructor 2
	 * 
	 * @param x
	 *            Latitude
	 * @param y
	 *            Longitude
	 * @param radius
	 *            Radius of the sensor (default value = 0 meters)
	 * @param radioRadius
	 *            Radius (range) of the radio (in meter)
	 */
	public FunctionalSensorNode(double x, double y, double z, double radius, double radioRadius, int id) {
		super(x, y, z, radius, radioRadius, id);
		sensorUnit = new FunctionalSensorUnit(this.longitude, this.latitude, this.elevation, this);		
	}
	
	/**
	 * Constructor 3
	 * 
	 * @param x
	 *            Latitude
	 * @param y
	 *            Longitude
	 * @param radius
	 *            Radius of the sensor (default value = 0 meters)
	 * @param radioRadius
	 *            Radius (range) of the radio (in meter)
	 * @param suRadius
	 *            Radius of the sensor unit (default value = 10 meters)
	 * @param dur
	 * 			duration of service (default is 30 days)
	 * @param roi
	 * 			region of interest (default is general interest)
	 */
	public FunctionalSensorNode(double x, double y, double z, double radius, double radioRadius,
			double suRadius, int id, double dur, String roi) {
		super(x, y, z, radius, radioRadius, id);
		sensorUnit = new FunctionalSensorUnit(this.longitude, this.latitude, this.elevation, suRadius, this, dur, roi);
	}
	
	/**
	 * Constructor 4
	 * 
	 * @param x
	 *            Latitude
	 * @param y
	 *            Longitude
	 * @param radius
	 *            Radius of the sensor (default value = 0 meters)
	 * @param radioRadius
	 *            Radius (range) of the radio (in meter)
	 * @param suRadius
	 *            Radius of the sensor unit (default value = 10 meters)
	 * @param sb
	 *            A two dimensional table that contains a set of informations
	 *            about the sensor (temperature, co2, etc.) The first column
	 *            contains the name of the parameter The second column contains
	 *            the value of the corresponding parameter
	 * @param dur
	 * 			duration of service (default is 30 days)
	 * @param roi
	 * 			region of interest (default is general interest)
	 */
	public FunctionalSensorNode(double x, double y, double z, double radius, double radioRadius, double suRadius, String[][] sb, int id, double dur, String roi) {
		this(x, y, z, radius, radioRadius, suRadius, id, dur, roi);
		this.setInfos(sb);
		initBuffer();
	}
	
	/**
	 * Constructor 5 the same as the Constructor 3 with "String" argument
	 * instead of "double"
	 * 
	 * @param x
	 *            Latitude
	 * @param y
	 *            Longitude
	 * @param radius
	 *            Radius of the sensor (default value = 0 meters)
	 * @param radioRadius
	 *            Radius (range) of the radio (in meter)
	 * @param suRadius
	 *            Radius of the sensor unit (default value = 10 meters)
	 * @param dur
	 * 			duration of service (default is 30 days)
	 * @param roi
	 * 			region of interest (default is general interest)
	 */
	public FunctionalSensorNode(String x, String y, String z, String radius, String radioRadius, String suRadius, int id, double dur, String roi) {
		super(Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Double.valueOf(radius), Double.valueOf(radioRadius), id);
		sensorUnit = new FunctionalSensorUnit(this.longitude, this.latitude, this.elevation, Double.valueOf(suRadius), this, dur, roi);
	}
	
	/**
	 * Constructor 6
	 * 
	 * @param x
	 *            Latitude
	 * @param y
	 *            Longitude
	 * @param radius
	 *            Radius of the sensor (default value = 0 meters)
	 * @param radioRadius
	 *            Radius (range) of the radio (in meter)
	 * @param suRadius
	 *            Radius of the sensor unit (default value = 10 meters)
	 * @param gpsFileName
	 *            The path of the GPS file
	 * @param scriptFileName
	 *            The path of the script file
	 * @param dur
	 * 			duration of service (default is 30 days)
	 * @param roi
	 * 			region of interest (default is general interest)
	 */
	public FunctionalSensorNode(String id, String x, String y, String z, String radius, String radioRadius,
			String suRadius, String gpsFileName, String scriptFileName, String dur, String roi) {
		this(x, y, z, radius, radioRadius, suRadius, Integer.valueOf(id), Double.valueOf(dur), roi);
//		String [] srd = rdInfos.split("#");
//		this.getCurrentRadioModule().setMy(Integer.valueOf(srd[0]));
//		this.getCurrentRadioModule().setCh(Integer.valueOf(srd[1]));
//		this.getCurrentRadioModule().setNId(Integer.valueOf(srd[2]));
		gpsFileName = (gpsFileName.equals("#") ? "" : gpsFileName);
		scriptFileName = (scriptFileName.equals("#") ? "" : scriptFileName);
		setGPSFileName(gpsFileName);
		setScriptFileName(scriptFileName);
		calculateRadioSpace();
		initBuffer();
	}
	
	@Override
	public void drawSensorUnit(Graphics g) {
		int[] coord = MapCalc.geoToPixelMapA(latitude, longitude);
		int x = coord[0];
		int y = coord[1];
		//if(hide == 0 || hide == 1) {
		if(hide == 0 || hide == 4) {
			sensorUnit.setPosition(x, y);
			sensorUnit.draw(g, 0, isSensorDetecting(), detectBuildings());
		}

		if(hide == 3) {
			sensorUnit.setPosition(x, y);
			sensorUnit.draw(g, 1, isSensorDetecting(), detectBuildings());
		}
	}
	
	@Override
	public SensorNode clone() throws CloneNotSupportedException {
		SensorNode newSensor = (SensorNode) super.clone();
		SensorUnit newCaptureUnit = (SensorUnit) sensorUnit.clone();
		Battery newBattery = (Battery) battery.clone();
		((FunctionalSensorNode)newSensor).setSensorUnit(newCaptureUnit);
		newCaptureUnit.setNode(newSensor);
		newSensor.setBattery(newBattery);
		return newSensor;
	}
	
	public double getSensorUnitDuration() {
		return ((FunctionalSensorUnit) sensorUnit).getDuration();
	}
	
	public String getSensorUnitRoi() {
		return ((FunctionalSensorUnit) sensorUnit).getRegionOfInterest();
	}
	
	public void setSensorUnitRadius(double radius) {
		((FunctionalSensorUnit) sensorUnit).setRadius(radius);
	}
	
	public void setSensorUnitDuration(double dur) {
		((FunctionalSensorUnit) sensorUnit).setDuration(dur);
	}
	
	public void setSensorUnitRoi(String roi) {
		((FunctionalSensorUnit) sensorUnit).setRegionOfInterest(roi);
	}
	
	@Override
	public SensorNode createNewWithTheSameType() {
		FunctionalSensorNode n_msn = new FunctionalSensorNode(longitude, latitude, elevation, radius, 0.0, DeviceList.number++);
		FunctionalSensorUnit c_msu = (FunctionalSensorUnit) this.getSensorUnit(); 
		FunctionalSensorUnit n_msu = (FunctionalSensorUnit) n_msn.getSensorUnit();
		
		n_msu.setDuration(c_msu.getDuration());
		n_msu.setRegionOfInterest(c_msu.getRegionOfInterest());
		n_msu.setRadius(c_msu.getRadius());
		return n_msn;
	}

	@Override
	public boolean detectBuildings() {
		return BuildingList.intersect(sensorUnit.getPoly());
	}

	@Override
	public String getParamsStr() {
		return getSensorUnitDuration()+ " " + getSensorUnitRoi();
	}

	@Override
	public double getNextValueTime() {
		return Double.MAX_VALUE;
	}

	@Override
	public void generateNextValue() {}

	@Override
	public void initBattery() {
		getBattery().init();
	}

	@Override
	public double getSensorUnitRadius() {
		return sensorUnit.getRadius();
	}

	@Override
	public void save(String ref) {
		String fileName = Project.getProjectNodePath();
		try {
			PrintStream fos = null;	
			fos = new PrintStream(new FileOutputStream(fileName + File.separator + "functionalsensor_" + ref));
			fos.println("List of parameters");
			fos.println("------------------------------------------");
			fos.println("device_type:" + getType());
			fos.println("device_id:" + getId());
			fos.println("device_longitude:" + getLongitude());
			fos.println("device_latitude:" + getLatitude());
			fos.println("device_elevation:" + getElevation());
			fos.println("device_radius:" + getRadius());
			fos.println("device_hide:" + getHide());
			fos.println("device_draw_battery:" + getDrawBatteryLevel());
			fos.println("device_sensor_unit_radius:" + getSensorUnitRadius());			
			if (!getGPSFileName().equals(""))
				fos.println("device_gps_file_name:" + getGPSFileName());
			if (!getScriptFileName().equals(""))
				fos.println("device_script_file_name:" + getScriptFileName());
			if (getType() == Device.FUNCTIONAL_SENSOR)
				fos.println("directional_parameters:" + ((FunctionalSensorNode) this).getParamsStr());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		saveRadioModule(Project.getProjectRadioPath() + File.separator + "functionalsensor_"+ref);

	}

	@Override
	public String getIdFL() {
		// TODO Auto-generated method stub
		return "F";
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return Device.FUNCTIONAL_SENSOR;
	}

}
