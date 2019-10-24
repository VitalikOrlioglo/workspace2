package application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;



/**
 * 
 * -Socket (Verbindung zum Server) erzeugen
 * -Nachricht/ Name zum Server senden
 * cimadata Java 2
 * @author micha schirmer 
 * 24.10.2019
 * A07_fx_TimeClient0.1
 *
 */
public class ClientService extends Service<String>{
	private static Logger log  = LogManager.getLogger();
	
	private String name;

	@Override
	protected Task<String> createTask() {
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				log.debug("call...");
				Socket toServerSocket = new Socket("localhost",1111);
				ObjectOutputStream out = new ObjectOutputStream(toServerSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(toServerSocket.getInputStream());
				out.writeObject(name);
				
				String serverMsg = String.valueOf( in.readObject());
				log.debug(serverMsg);
				toServerSocket.close();
				return serverMsg;
			}
		};
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
