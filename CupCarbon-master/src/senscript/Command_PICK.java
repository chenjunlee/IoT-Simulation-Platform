package senscript;

import device.SensorNode;
import simulation.WisenSimulation;

public class Command_PICK extends Command {

	protected String arg = ""; 
	
	public Command_PICK(SensorNode sensor, String arg) {
		this.sensor = sensor ;
		this.arg = arg ;
	}

	@Override
	public double execute() {
		WisenSimulation.simLog.add("S" + sensor.getId() + " PICK");
		// default on pick message on buffer 1
		sensor.pickMessage(arg,1);
		return 0 ;
	}

	@Override
	public String toString() {
		return "PICK";
	}
	
}
