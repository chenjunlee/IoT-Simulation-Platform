package user;

import java.awt.Graphics2D;
import java.util.Vector;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import device.SensorNode;
import map.MapLayer;

public class UserList {
	public static Vector<User> users = new Vector<User>();
	public static int currentUser = -1;
	public static int lastUser = -1;

	public UserList() {
		reset();
	}

	public static void reset() {
//		users = new Vector<User>();
	}

	public static void add(User u){
		users.add(u);
	}

	public void drawUserAreas(Graphics2D g){
		for(User u: users)
			u.drawConcernedAreaOnMap(g);

		if(currentUser >=0 )
			users.get(currentUser).drawBackgroundCurrentUser(g);
	}
	
	/**
	 * @author Yiwei Yao
	 * @param userData
	 * parse UserData and add into UserList
	 */
	public static void openFromDB(FindIterable<Document> userData) {
		//Routes.reset();
		resetFromDB();
		MongoCursor<Document> userDataIterator = userData.iterator();
		while(userDataIterator.hasNext()) {
			Document SelectedUser = userDataIterator.next();
			User user = new User(SelectedUser.getString("name"));
			user.setLatitude1(SelectedUser.getDouble("latitude1"));
			user.setLatitude2(SelectedUser.getDouble("latitude2"));
			user.setLongitude1(SelectedUser.getDouble("longitude1"));
			user.setLongitude2(SelectedUser.getDouble("longitude2"));
			user.setTemperatureSensing(SelectedUser.getBoolean("temperatureSensing"));
			user.setHumiditySensing(SelectedUser.getBoolean("humiditySensing"));
			user.setGasSensing(SelectedUser.getBoolean("gasSensing"));
			user.setLightSensing(SelectedUser.getBoolean("lightSensing"));
			user.setWindLevelSensing(SelectedUser.getBoolean("windLevelSensing"));
			user.setWaterLevelSensing(SelectedUser.getBoolean("waterLevelSensing"));
			user.setDataEncrypted(SelectedUser.getBoolean("dataEncrypted"));
			user.setPreferredLatency(SelectedUser.getDouble("preferredLatency"));
			user.setPreferredThroughput(SelectedUser.getDouble("preferredThroughput"));
			user.setPreferredFrequency(SelectedUser.getLong("preferredFrequency"));
			user.setSelectedArea(SelectedUser.getBoolean("selectedArea"));
			users.add(user);
		}
//		MapLayer.repaint();
		for(User u: users) {
			u.getSensorsInsideArea();
		}
	}

	public static void resetFromDB() {
		users = new Vector<User>();
	}
}
