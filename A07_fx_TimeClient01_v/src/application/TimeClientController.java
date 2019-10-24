package application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TimeClientController {
	
	private static Logger log = LogManager.getRootLogger();
	private ClientService clientService;
	
    @FXML
    private TextField msgTextField;

    @FXML
    private Label antwortLabel;

    @FXML
    void sendAction(ActionEvent event) {
    	log.info("Nachricht zum Server");
    	clientService.setName(msgTextField.getText());
    	clientService.restart();
    }

    @FXML
    void initialize() {
    	log.info("init Controller");
    	clientService = new ClientService();
    	
    	antwortLabel.textProperty().bind(clientService.valueProperty());
    	clientService.exceptionProperty().addListener((a,oldValue,newValue)->{
			if(newValue!=null) {
				Throwable ex = newValue;
				ex.printStackTrace();
			}
		}) ;
    }
}
