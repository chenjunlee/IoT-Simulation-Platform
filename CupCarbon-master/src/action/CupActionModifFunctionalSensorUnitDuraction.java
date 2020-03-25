package action;

import device.SensorNode;
import sensorunit.FunctionalSensorUnit;

/**
 * UI Interaction method set duration
 * @author Yiwei Yao
 */

public class CupActionModifFunctionalSensorUnitDuraction extends CupAction {
	
	private SensorNode sensorNode;
	private double duration;
	private double cDuration;
	
	public CupActionModifFunctionalSensorUnitDuraction(SensorNode sensorNode, double cDuration, double duration) {
		super();
		this.sensorNode = sensorNode;
		this.duration = duration;
		this.cDuration = cDuration;
	}
	
	@Override
	public void execute() {
		((FunctionalSensorUnit) sensorNode.getSensorUnit()).setDuration(duration);
	}

	@Override
	public void antiExecute() {
		((FunctionalSensorUnit) sensorNode.getSensorUnit()).setDuration(cDuration);
	}
}
