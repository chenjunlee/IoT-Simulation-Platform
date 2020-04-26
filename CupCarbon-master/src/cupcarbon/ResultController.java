package cupcarbon;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;
import org.bson.json.JsonReader;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import database.DBMethods;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/**
 * @author Yiwei Yao
 *
 *DBProjectSelectController control the windows of dbprojectselect.fxml
 */
public class ResultController implements Initializable {

	@FXML
	public ComboBox<String> projectName;

	@FXML
	private ComboBox<String> userName;
	
	@FXML
	private ComboBox<String> sensorName;
	
	@FXML
	private TextArea data;
	
	private String tmp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initProjectName();
		projectName.getSelectionModel()
		.selectedItemProperty()
		.addListener( (options, oldValue, newValue) -> {
			if(oldValue != newValue)
				loadUser(newValue);
				tmp = newValue;
					});
		userName.getSelectionModel()
		.selectedItemProperty()
		.addListener( (options, oldValue, newValue) -> {
			if(oldValue != newValue)
				loadSensor(tmp);
					});
		sensorName.getSelectionModel()
		.selectedItemProperty()
		.addListener( (options, oldValue, newValue) -> {
			if(oldValue != newValue)
				loadData(tmp, newValue);
					});
	}

	
	/**
	 * get a list of collection name in iot db
	 */
	private void initProjectName() {
		MongoDatabase db = DBMethods.getDB("iot");
		MongoIterable<String> collections = DBMethods.getCollections(db);
		MongoCursor<String> collectionsIterator = collections.iterator();
		while(collectionsIterator.hasNext()) {
			projectName.getItems().add(collectionsIterator.next());
		}
	}

	private void loadUser(String currentProject) {
		userName.getItems().clear();
		MongoCollection<Document> project = DBMethods.getDB("iot_user").getCollection(currentProject);
		FindIterable<Document> userData = DBMethods.findWithPrefix(project, "user");
		MongoCursor<Document> userDataIterator = userData.iterator();
		while(userDataIterator.hasNext()) {
			Document SelectedUser = userDataIterator.next();
			userName.getItems().add(SelectedUser.getString("name"));
		}
	}
	
	private void loadSensor(String currentProject) {
		sensorName.getItems().clear();
		MongoCollection<Document> project = DBMethods.getDB("iot_result").getCollection(currentProject);
		FindIterable<Document> sensorData = DBMethods.find(project);
		MongoCursor<Document> sensorDataIterator = sensorData.iterator();
		while(sensorDataIterator.hasNext()) {
			Document SelectedSensor = sensorDataIterator.next();
			sensorName.getItems().add(SelectedSensor.getString("sensor"));
		}
	}
	
	private void loadData(String currentProject, String sensor) {
		data.clear();
		MongoCollection<Document> project = DBMethods.getDB("iot_result").getCollection(currentProject);
		FindIterable<Document> sensorData = DBMethods.find(project);
		MongoCursor<Document> sensorDataIterator = sensorData.iterator();
		while(sensorDataIterator.hasNext()) {
			Document SelectedSensor = sensorDataIterator.next();
			if(SelectedSensor.getString("sensor").equals(sensor)) {
//				SelectedSensor.
//				data.appendText(SelectedSensor.toString());
			}
//			sensorName.getItems().add(SelectedSensor.getString("sensor"));
		}
	}
}
