package action;

import device.SensorNode;
import sensorunit.FunctionalSensorUnit;

/**
 * UI Interaction method set Region of Interest
 * @author Yiwei Yao
 */

public class CupActionModifFunctionalSensorUnitRoi extends CupAction {

	private SensorNode sensorNode;
	private String Roi;
	private String cRoi;
	
	public CupActionModifFunctionalSensorUnitRoi(SensorNode sensorNode, String cRoi, String Roi) {
		super();
		this.sensorNode = sensorNode;
		this.Roi = Roi;
		this.cRoi = cRoi;
	}
	@Override
	public void execute() {
		((FunctionalSensorUnit) sensorNode.getSensorUnit()).setRegionOfInterest(Roi);

	}

	@Override
	public void antiExecute() {
		((FunctionalSensorUnit) sensorNode.getSensorUnit()).setRegionOfInterest(cRoi);
	}

}
