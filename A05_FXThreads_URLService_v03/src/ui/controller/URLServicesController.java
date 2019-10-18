package ui.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.URLService;
import javafx.scene.control.Label;

public class URLServicesController {
	private URLService urlService;
	private ExtractURLService extractURLService;
	
	
	@FXML private TextField textField;
	@FXML private TextArea textArea;

	@FXML TextArea sourceCodeArea;

	@FXML ListView<String> listView;

	@FXML TextArea linksArea;


	@FXML Label infoLabel;
		

		
	@FXML public void searchButton(ActionEvent event) {
		urlService.setUrlString(textField.getText());
		urlService.restart();
	}
		
		
	@FXML void initialize() {
		System.out.println("init . . .");
		urlService = new URLService();
		infoLabel.textProperty().bind(urlService.messageProperty());
		extractURLService = new ExtractURLService();
		sourceCodeArea.textProperty().bind(urlService.valueProperty());
		
		// Exception fur urlService
		urlService.exceptionProperty().addListener((a, oldValue, newValue) -> {
			if (newValue!=null) {
				Throwable ex = newValue;
				ex.printStackTrace();
			}
		});
		
		// Exception fur extractURLService
		extractURLService.exceptionProperty().addListener((a, oldValue, newValue) -> {
			if (newValue!=null) {
				Throwable ex = newValue;
				ex.printStackTrace();
			}
		});
		
		urlService.setOnSucceeded(e->{
			extractURLService.setHtmlSourceCode(urlService.getValue()); // oder von (TextArea.getText())
			extractURLService.restart();
		});
		
		extractURLService.valueProperty().addListener((a, b, newValue) ->{
			listView.getItems().setAll(newValue);
		});
	}

}
