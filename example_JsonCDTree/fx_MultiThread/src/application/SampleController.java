package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class SampleController implements Initializable {

	@FXML
	private ProgressBar pg1;
	@FXML
	private ProgressBar pg2;
	@FXML
	private ProgressBar pg3;
	@FXML
	private ProgressBar pg4;
	@FXML
	private ProgressBar pg5;
	@FXML
	private ProgressBar pg8;
	@FXML
	private ProgressBar pg6;
	@FXML
	private ProgressBar pg7;
	@FXML
	private Label labelT1;
	@FXML
	private Label labelT2;
	@FXML
	private Label labelT3;
	@FXML
	private Label labelT4;
	@FXML
	private Label labelT5;
	@FXML
	private Label labelT6;
	@FXML
	private Label labelT7;
	@FXML
	private Label labelT8;

	MyService[] services;
	//Task<String>[] tasks;

	Object obj = new Object();
	// Event Listener on Button.onAction
	@FXML
	public void startAll(ActionEvent event) {

		for (MyService service : services) {
			service.restart();
		}
	}

	

	

	@FXML
	private void stopAll(){
		for (MyService service : services) {
			service.cancel();
		}
	}
	
	@FXML
	private void reset () {
		init();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		init();

	}

	private void init() {
		ProgressBar[] pgList = {pg1, pg2, pg3, pg4, pg5, pg6,pg7, pg8};
		Label[] labelList = {labelT1,labelT2,labelT3,labelT4,labelT5,labelT6,labelT7,labelT8};
		services = new MyService[8];
		for (int i = 0; i < 8; i++) {
			MyService service = new MyService();
			pgList[i].progressProperty().bind(service.progressProperty());
			labelList[i].textProperty().bind(service.stateProperty().asString());
			
			
			
			services[i] = service;
			
			
		}
	}
}
