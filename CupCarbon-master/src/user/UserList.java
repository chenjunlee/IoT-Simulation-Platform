package user;

import java.awt.Graphics2D;
import java.util.Vector;

public class UserList {
	public static Vector<User> users = new Vector<User>();
	public static int currentUser = -1;
	public static int lastUser = -1;

	public UserList() {
		reset();
	}

	public static void reset() {
		//users = new Vector<User>();
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

}
