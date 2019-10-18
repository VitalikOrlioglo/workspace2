package ui.controller;

import javax.naming.ldap.ExtendedRequest;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.ExctractURLService;
import service.URLService;

public class URLServicesController {

	private URLService urlservice;
	private ExctractURLService extractURLService;
	
    
    @FXML
    private TextArea sourceTextArea;

    @FXML
    private ListView<String> linkListView;

    @FXML
    private TextField searchTextField;


	@FXML 
	private Label infoLabel;

    @FXML
    void onSearchAction(ActionEvent event) {
    	urlservice.setUrlString(searchTextField.getText());
    	urlservice.restart();
    }

    @FXML
    void initialize() {
    	System.out.println("init...");
    	urlservice = new URLService();
    	extractURLService = new ExctractURLService();
    	infoLabel.textProperty().bind(urlservice.messageProperty());
    
    	sourceTextArea.textProperty().bind(urlservice.valueProperty());// return von call
    	// Exception
    	urlservice.exceptionProperty().addListener((a,oldValue,newValue)->{
			if(newValue!=null) {
				Throwable ex = newValue;
				ex.printStackTrace();
			}
		}) ;
    	extractURLService.exceptionProperty().addListener((a,oldValue,newValue)->{
			if(newValue!=null) {
				Throwable ex = newValue;
				ex.printStackTrace();
			}
		}) ;
    	urlservice.setOnSucceeded( e->{
    		extractURLService.setHtmlSourceCode(urlservice.getValue());// oder von TextArea.getText()
    		extractURLService.restart();
    	} );
    	
    	extractURLService.valueProperty().addListener( (a,b,newValue)->{
    		linkListView.getItems().setAll(newValue);
    	});

    }
}
