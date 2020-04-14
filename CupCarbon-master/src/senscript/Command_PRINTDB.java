package senscript;

import java.util.Arrays;

import device.SensorNode;
import simulation.WisenSimulation;

public class Command_PRINTDB extends Command {
	
	protected String [] arg ;

	public Command_PRINTDB(SensorNode sensor, String [] arg) {
		this.sensor = sensor ;
		this.arg = arg ;
	}
	
	@Override
	public double execute() {
		WisenSimulation.simLog.add("S" + sensor.getId() + " PRINTDB "+Arrays.toString(arg));
		String message = "";
		String part = "";
		for (int i=1; i<arg.length; i++) {
			part = sensor.getScript().getVariableValue(arg[i]); 
			message += part + " ";
		}
		sensor.getScript().printToDB(message);
		return 0 ;
	}
	
	@Override
	public String toString() {
		return "PRINTDB";
	}

}
