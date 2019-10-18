package application;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class URLService extends Service<String> {
	URL url;
	StringBuilder sb;
//	String urlString = "http://www.google.de/";
	String urlString;//"http://localhost/dashboard/";

	
	@Override
	protected Task<String> createTask() {
		Task<String> task = new Task<String>() {

			@Override
			protected String call() throws Exception {
				try {
					url = new URL(urlString);
					sb = new StringBuilder();
					Scanner sc = new Scanner(url.openStream()); 

					while (sc.hasNext()) {
						String str1="";
						str1=sc.nextLine();
						sb.append(str1+"\n");
					}
				}catch ( IOException e) {
					e.printStackTrace(); 
				}
				
				return sb.toString();//result
			}
			
		};// Task<String> task = new Task<String>() {
		return task;

	}// protected Task<String> createTask() {


	public void setUrl(String urlString2) {
		this.urlString= urlString2;
		
	}
}// class


