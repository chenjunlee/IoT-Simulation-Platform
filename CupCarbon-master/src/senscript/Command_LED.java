package senscript;

import java.util.Vector;

import arduino.BeginInstructions;
import device.CloudServer;
import device.SensorNode;
import simulation.WisenSimulation;


public class Command_LED extends Command {

	protected String arg1 = null ;
	protected String arg2 = null ;
	
	// Note: arg1 is the number of the pin: not used in simulation
	// but it is useful for the code generation to determine which pin
	// will be used for the led
	
	public Command_LED(SensorNode sensor, String arg1, String arg2) {
		this.sensor = sensor ;
		this.arg1 = arg1 ;
		this.arg2 = arg2 ;
	}

	@Override
	public double execute() {
		String tableName = sensor.getScript().getVariableValue(arg1);
		String data = sensor.getScript().getVariableValue(arg2);
		if(sensor.getClass().equals(CloudServer.class)) {
			Vector<SensorNode> sensors = sensor.getUser().getSensorsInsideArea();
			int count = sensors.size();
			sensor.getScript().putVector(tableName, count+1);
			String [] vector = sensor.getScript().getVector(tableName);
			vector[0] = "" + (count +1);
			int index = 1;
			for(SensorNode s : sensors) {
				vector[index] ="" +s.getId();
				index++;
			}
		} else {
			sensor.getScript().putVector(tableName, 1);
			String [] vector = sensor.getScript().getVector(tableName);
			vector[0] = ""+ 1;
		}
		
		return 0;
	}

	@Override
	public String getArduinoForm() {
		BeginInstructions.add("pinMode("+arg1+", OUTPUT);");
		String s = "";
		String v = "'"+arg2.charAt(0)+"'";
		if(arg2.charAt(0)=='$') v = arg2.substring(1)+".charAt(0)";
		
		s = "\tdigitalWrite(13, 1-('1'-"+v+"));";
		
		return s;
	}
	
	@Override
	public String toString() {
		return "LED";
	}
	
}
