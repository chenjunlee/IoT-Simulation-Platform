package sensorunit;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.Point2D;

import org.jdesktop.swingx.mapviewer.GeoPosition;

import device.Device;
import map.MapLayer;
import utilities.MapCalc;
import utilities.UColor;

/**
 * New Sensor Unit with desired preferences like duration of service, sensing frequency, the region of interest and so on
 * @author Yiwei Yao
 */
public class FunctionalSensorUnit extends SensorUnit {

	
	protected double deg = 0.209333;
	
	/**
	 * Constructor 1 : radius is equal to 10 meter
	 * @param x Position of the sensor unit on the map
	 * @param y Position of the sensor unit on the map
	 * @param node which is associated to this sensor unit
	 */
	public FunctionalSensorUnit(double longitude, double latitude, double elevation, Device node) {
		super(longitude, latitude, elevation, node);		
		radius = 10;
		n = 30;
		deg = 0.209333;
		calculateSensingArea();
		//MapLayer.mapViewer.addKeyListener(this);
	}
	
	/**
	 * Constructor 2 : radius is equal to 10 meter
	 * @param x Position of the sensor unit on the map
	 * @param y Position of the sensor unit on the map
	 * @param cuRadius the value of the radius 
	 * @param node which is associated to this sensor unit
	 */
	public FunctionalSensorUnit(double longitude, double latitude, double elevation, double radius, Device node) {
		this(longitude, latitude, elevation, node);
		this.radius = radius;
		calculateSensingArea();
	}
	
	/**
	 * Constructor 3 : radius is equal to 10 meter
	 * @param x Position of the sensor unit on the map
	 * @param y Position of the sensor unit on the map
	 * @param cuRadius the value of the radius 
	 * @param node which is associated to this sensor unit
	 * @param duration which is duration of service
	 * @param regionOfInterest which is region of interest
	 */
	public FunctionalSensorUnit(double longitude, double latitude, double elevation, double radius, Device node, double duration, String regionOfInterest) {
		this(longitude, latitude, elevation, node);
		this.radius = radius;
		this.duration = duration;
		this.regionOfInterest = regionOfInterest;
		calculateSensingArea();
	}
	
	@Override
	public void calculateSensingArea() {
		int rayon = MapCalc.radiusInPixels(radius) ; 
		
		double r2=0;
		double r3=0;
		
		double i=0.0;
		for(int k=0; k<n; k++) {
			r2 = rayon*Math.cos(i);
			r3 = rayon*Math.sin(i);
			polyX[k]=(int)(longitude+r2);
			polyY[k]=(int)(latitude+r3);
			i+=deg;
		}
	}
	
	/**
	 * Set the radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	/**
	 * Change the position of the sensor unit
	 */
	public void setPosition(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/**
	 * Change the duration of the sensor unit
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	/**
	 * Change the region of interest of the sensor unit
	 */
	public void setRegionOfInterest(String regionOfInterest) {
		this.regionOfInterest = regionOfInterest;
	}
	
	@Override
	public boolean detect(Device device) {
		if(device.getRadius()>0) {
			Polygon poly = new Polygon(polyX, polyY, n);
			GeoPosition gp = new GeoPosition(device.getLatitude(), device.getLongitude());
			Point2D p1 = MapLayer.mapViewer.getTileFactory().geoToPixel(gp, MapLayer.mapViewer.getZoom());		
			return (poly.contains(p1));
		}
		else
			return false;
	}

	@Override
	public void draw(Graphics g, int mode, boolean detection, boolean buildingDetection) {
		 calculateSensingArea();
		if (!detection)
			g.setColor(UColor.WHITE_LLTRANSPARENT);
		else
			g.setColor(UColor.YELLOW_SENSOR_TR);
	
		if (mode == 0)
			g.fillPolygon(polyX, polyY, n);
		g.setColor(UColor.BLACK_TTTRANSPARENT);
		g.drawPolygon(polyX, polyY, n);

	}

	@Override
	public void incRadius(int u) {
		radius += u;
	}

	/**
	 * Clone the sensor unit
	 */
	@Override
	public StdSensorUnit clone() throws CloneNotSupportedException {
		StdSensorUnit newSU = (StdSensorUnit) super.clone();
		//MapLayer.mapViewer.addKeyListener(newCU);
		return newSU;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}
	
	public double getESensing() {
		return eSensing;
	}

	public void setESensing(double eSensing) {
		this.eSensing = eSensing;
	}

	@Override
	public double getCoverage() {
		return 0;
	}

	@Override
	public double getDirection() {
		return 0;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public String getRegionOfInterest() {
		return regionOfInterest;
	}
}
