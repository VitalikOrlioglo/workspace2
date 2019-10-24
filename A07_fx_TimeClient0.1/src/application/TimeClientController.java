package application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TimeClientController {
	
	private static Logger log  = LogManager.getLogger();
	
	private ClientService clientservice;

    @FXML
    private TextField msgTextField;

    @FXML
    private Label responseLabel;

    @FXML
    void sendAction(ActionEvent event) {
    	log.info("Nachricht zum Server");
    	clientservice.setName(msgTextField.getText());
    	clientservice.restart();
    }

    @FXML
    void initialize() {
    	log.info("init Controller");
    	clientservice = new ClientService();
    	
    	responseLabel.textProperty().bind(clientservice.valueProperty());
    	clientservice.exceptionProperty().addListener((a,oldValue,newValue)->{
			if(newValue!=null) {
				Throwable ex = newValue;
				ex.printStackTrace();
			}
		}) ;

    }
}
