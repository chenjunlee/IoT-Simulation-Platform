package senscript;

import java.util.Arrays;

import device.SensorNode;
import simulation.WisenSimulation;

/**
 * @author Yiwei Yao
 *
 * handle printdb command
 * printdb String[] arg
 * example:
 * printdb hello 12 23 34
 * 
 * in Mongodb:
 * sensorid: XXX
 * 12:31:00 4/19/20: hello 12 23 34
 */
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
