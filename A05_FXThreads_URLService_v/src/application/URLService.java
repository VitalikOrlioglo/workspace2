package application;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;

public class URLService{

	@FXML TextField textFieldId;
	@FXML TextArea textAreaId;


	@FXML public void startButton(ActionEvent event) {
		WebView webView = new WebView();
		webView.getEngine().load("http://google.com");
	}
//	@FXML
    void buildWebView() {
//		WebView webView = new WebView();
//	    webView.getEngine().load("http://google.com");
////	    return webView;
    }	
}
