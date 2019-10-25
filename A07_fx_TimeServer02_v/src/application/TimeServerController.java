package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import service.ServerService;

public class TimeServerController {
	private static Logger log = LogManager.getRootLogger();
	
	private ServerService serverService;
	
	@FXML
    private Label infoLabel;
	

    @FXML
    void serverStartAction(ActionEvent event) throws IOException {//5.
    	log.info("Server start Action");
    	serverService.restart();//6.
    }

    @FXML
    void initialize() {
    	serverService = new ServerService(); //1. 
    	
    	infoLabel.textProperty().bind(serverService.messageProperty());
    	serverService.exceptionProperty().addListener((a,oldValue,newValue)->{ //2.
			if(newValue!=null) {
				Throwable ex = newValue;
				ex.printStackTrace();
			}
		}) ;
//    	
//    	service.exceptionProperty().addListener((a,oldValue,newValue)->{
//			if(newValue!=null) {
//				Throwable ex = newValue;
//				ex.printStackTrace();
//			}
//		}) ;
    }
}
