package database;

import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * @author Yiwei Yao
 *
 */
public class DBMethods {	
	static MongoClient  mongo = ConnectToDB.mongoClient;
	
	//get db
	public static MongoDatabase getDB(String dbName) {
		return mongo.getDatabase(dbName);
	}
	
	//get a list of collections
	public static MongoIterable<String> getCollections(MongoDatabase db) {
		return db.listCollectionNames();
	}
	
//	get the number of results in a collection
	public static long collectionsCount(MongoCollection<Document> collection) {
		return collection.count();
	}
	
	// get a collection
	public static MongoCollection<Document> getCollection(MongoDatabase db, String collection) {
		return db.getCollection(collection);
	}
	
	// read operation
	public static FindIterable<Document> find(MongoCollection<Document> collection) {
		return collection.find();
	}
	
	// find document with prefix
	public static FindIterable<Document> findWithPrefix(MongoCollection<Document> collection, String prefix) {
		BasicDBObject query = new BasicDBObject();
		query.put("prefix", prefix);
		FindIterable<Document> data = collection.find(query);
		return data;
	}
	
	// drop collection
	public static void dropCollection(MongoCollection<Document> collection) {
		collection.drop();
	}
	
	// write operation
//	public static void insert(Document document, MongoCollection<Document> collection) {
//		collection.insertOne(document);
//	}
}
