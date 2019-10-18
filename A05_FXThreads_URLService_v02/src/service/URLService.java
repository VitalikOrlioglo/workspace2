package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;

public class URLService extends Service<String>{
	private String urlString;

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

	@Override
	protected Task<String> createTask() {
		return new Task<String>() {
			@Override
			protected String call() throws Exception {
				updateMessage("URLService call . . .");
				URL url = new URL(urlString);
				
				StringBuilder sb = new StringBuilder();
				// StringBuilder → besser als mit += zu arbeiten
				// Der StringBuilder besitzt die Methode append, um Zeichenketten // anzuhängen. Mit sb.toString() kann man wieder zu String // konvertieren
				
				
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					sb.append(sc.nextLine());
					sb.append(System.lineSeparator());
				}
				sc.close();
				updateMessage("URLService call end . . .");
				return sb.toString();
			}
		};
	}	
}
