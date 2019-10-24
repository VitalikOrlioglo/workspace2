package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.ServerService;

public class TimeServerController {
	
	private static Logger log  = LogManager.getLogger();
	
	
	
	private ServerService serverService;

  

    @FXML
    private Label infoLabel;

    @FXML
    void serverStartAction(ActionEvent event) {
    	log.info("Server start Action");
    	serverService.restart();

    }

    @FXML
    void initialize() {
    	serverService = new ServerService();
    	
    	infoLabel.textProperty().bind(serverService.messageProperty());
    	serverService.exceptionProperty().addListener((a,oldValue,newValue)->{
    		if(newValue!=null) {
    			Throwable ex = newValue;
    			ex.printStackTrace();
    		}
    	}) ;
//    	service.exceptionProperty().addListener((a,oldValue,newValue)->{
//			if(newValue!=null) {
//				Throwable ex = newValue;
//				ex.printStackTrace();
//			}
//		}) ;


    }
}
