package cupcarbon;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import database.DBMethods;
import database.ExportToClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DBProjectSelectController implements Initializable {

	@FXML
	private Button done;

	@FXML
	private ComboBox<String> txtLoadProjectName;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initProjectName();
	}

	
	private void initProjectName() {
		MongoDatabase db = DBMethods.getDB("iot");
		MongoIterable<String> collections = DBMethods.getCollections(db);
		MongoCursor<String> collectionsIterator = collections.iterator();
		while(collectionsIterator.hasNext()) {
			txtLoadProjectName.getItems().add(collectionsIterator.next());
		}
	}


	@FXML
	public void select() {
		boolean success = false;
		if (!txtLoadProjectName.getSelectionModel().getSelectedItem().isEmpty()) {
			String selectedProjectName = txtLoadProjectName.getSelectionModel().getSelectedItem().toString();
			ExportToClient.openProject(selectedProjectName);
			success = true;
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!");
			alert.setHeaderText(null);
			alert.setContentText("No Project Selected");
			alert.showAndWait();
		}
		if(success) {
		    Stage stage = (Stage) done.getScene().getWindow();
		    stage.close();
		    Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!");
			alert.setHeaderText(null);
			alert.setContentText("Operation is successed!");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning!");
			alert.setHeaderText(null);
			alert.setContentText("Operation is not successed!");
			alert.showAndWait();
		}
	}

}
