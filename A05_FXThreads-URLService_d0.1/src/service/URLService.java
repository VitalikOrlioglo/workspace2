package service;

import java.net.URL;
import java.util.Scanner;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class URLService extends Service<String> {
	
	private String urlString;

	@Override
	protected Task<String> createTask() {
		
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				updateMessage("URLService call...");
				URL url = new URL(urlString);
				StringBuilder sb = new StringBuilder();
				// StringBuilder → besser als mit += zu arbeiten
				// Der StringBuilder besitzt die Methode append, um Zeichenketten
				// anzuhängen. Mit sb.toString() kann man wieder zu String
				// konvertieren
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext()) {
					sb.append(sc.nextLine());
					sb.append(System.lineSeparator());
				}
				sc.close();
				updateMessage("URLService call end...");
				return sb.toString();
			}
		};
	}

	public String getUrlString() {
		return urlString;
	}

	public void setUrlString(String urlString) {
		this.urlString = urlString;
	}

}
