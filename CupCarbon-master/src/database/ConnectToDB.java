package database;

import com.mongodb.MongoClient;

public class ConnectToDB {
	
	private ConnectToDB() {};
	
	public static MongoClient mongoClient = new MongoClient( "127.0.0.1" );
	
// test client
//	public static void main(String[] args) {
//
//		MongoClient mongoClient = new MongoClient( "127.0.0.1" );
//
//	}
}
