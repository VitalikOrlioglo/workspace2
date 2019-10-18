/**
 * Sample Skeleton for 'URLServices.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class URLServicesController {

	String urlString="http://localhost/dashboard/";
	URLService urlService= null;
	ExtractURLService extractUrlService=null;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="textAreaSource"
    private TextArea textAreaSource; // Value injected by FXMLLoader

//    @FXML // fx:id="textAreaLink"
//    private TextArea textAreaLink; // Value injected by FXMLLoader
    
    @FXML
    private TextField textFieldUrl;
    
    @FXML // fx:id="listView"
    private ListView<Hyperlink> listView; // Value injected by FXMLLoader

    @FXML
    void onStart(ActionEvent event) {
    	
    	urlService.setUrl(textFieldUrl.getText());
    	urlService.restart();
    	System.out.println("start geklickt");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	System.out.println("starten");
//    	textAreaLink.setText("hallo link");
    	textFieldUrl.setText("https://www.cimdata.de/service/");
		urlService = new URLService();
		extractUrlService= new ExtractURLService();
		
    	urlService.setOnSucceeded(e ->{//erster Thread beendet (Source)
			
	    	textAreaSource.setText(urlService.getValue());
			extractUrlService.setUrlText(urlService.getValue());
			System.out.println("onsucc");			
			extractUrlService.restart();//START 2. Thread
		});
    	
    	extractUrlService.setOnSucceeded(e ->{//2. Thread beendet (Link)
    		textFieldUrl.textProperty().bind(extractUrlService.selectedLinkProperty());// tut gehen!!!
    		
//    		textAreaLink.setText(extractUrlService.getValue());//tableview.setItems(list);
    		listView.getItems().setAll(extractUrlService.getValue());

    		
    	});

    }
    
    
}
