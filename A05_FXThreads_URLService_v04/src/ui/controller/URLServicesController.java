package ui.controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.ExtractURLService;
import service.URLService;

public class URLServicesController {
	private URLService urlService;
	private ExtractURLService extractURLService;
	
	
	@FXML private TextField textField;
	@FXML private TextArea textArea;

	@FXML TextArea sourceCodeArea;

	@FXML ListView<Hyperlink> listView;

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
			listView.setItems(createHyperLinks(newValue));
		});
	}


	private ObservableList<Hyperlink> createHyperLinks(List<String> newValue) {
		ObservableList<Hyperlink> hlist = FXCollections.observableArrayList();
		for (Hyperlink stringLink : oldList) {
			Hyperlink hlink = new Hyperlink(stringLink);
			hlink.getOnAction(e->{
			te
			urlService.setUrlString(textField.getText());
			urlService.restart();	
				
			});
			hlist.add(hlink);
		}
		return hlist;
	}


}
