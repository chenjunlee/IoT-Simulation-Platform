package database;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import action.CupActionStack;
import buildings.BuildingList;
import cupcarbon.CupCarbon;
import device.DeviceList;
import map.NetworkParameters;
import markers.MarkerList;
import markers.Routes;
import project.Project;
import visibility.VisibilityLauncher;

public class ExportToClient {
	
	public static void openProject(String projectName) {
		CupCarbon.cupCarbonController.displayPermanentMessage_th("Loading ...");
		MongoCollection<Document> project = DBMethods.getDB("iot").getCollection(projectName);
		DeviceList.propagationsCalculated = false;
		System.out.println(projectName);
		System.out.println("Load From DataBase");
		CupActionStack.init();
		Project.reset();
		Project.setProjectName("DataBase Mode", projectName);
		FindIterable<Document> buildingData = DBMethods.findWithPrefix(project, "building");
		FindIterable<Document> deviceData = DBMethods.findWithPrefix(project, "device");
		FindIterable<Document> radioData = DBMethods.findWithPrefix(project, "radio_module");
		FindIterable<Document> markData = DBMethods.findWithPrefix(project, "mark");
		FindIterable<Document> projectData = DBMethods.findWithPrefix(project, "project");
		FindIterable<Document> simulationData = DBMethods.findWithPrefix(project, "simulation");
		try {
			BuildingList.openFromDB(buildingData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DeviceList.openFromDB(deviceData, radioData);
		MarkerList.openFromDB(markData);
		Project.loadParametersFromDB(projectData);
		CupCarbon.cupCarbonController.loadSimulationParams();
		CupCarbon.cupCarbonController.applyParameters();
		CupCarbon.cupCarbonController.saveButton.setDisable(false);
		if(DeviceList.propagationsCalculated) {
			DeviceList.calculatePropagations();
			VisibilityLauncher.calculate();
		}				
		if(NetworkParameters.displayAllRoutes) {
			MarkerList.reset();
			Routes.loadRoutes();
		}
		else 
			Routes.hideAll();
		
		CupCarbon.cupCarbonController.displayShortGoodMessage_th("Project loaded");
//				}
//				else {
//					CupCarbon.cupCarbonController.displayLongErrMessageTh("Project does not exist!");
//				}
//			}
//		});
//		th.start();
	}
	
	//test client
//	public static void main(String[] args) {
//
//		openProject("deviceTest");
//
//	}
}
