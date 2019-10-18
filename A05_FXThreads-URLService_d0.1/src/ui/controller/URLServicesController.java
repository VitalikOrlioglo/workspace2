package ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import service.URLService;

public class URLServicesController {

	private URLService urlservice;
	
    
    @FXML
    private TextArea sourceTextArea;

    @FXML
    private ListView<?> linkListView;

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
    	infoLabel.textProperty().bind(urlservice.messageProperty());
    	sourceTextArea.textProperty().bind(urlservice.valueProperty());// return von call
    	// Exception

    }
}
