package application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * - Socket(Verbindung zum Server) erzeugen
 * - Nachricht/Name zum Server senden
 * Student
 * @author vitali orlioglo
 * 24.10.2019
 * A07_fx_TimeClient01_v
 */
public class ClientService extends Service<String>{
	private static Logger log = LogManager.getRootLogger();
	
	private String name;

	@Override
	protected Task<String> createTask() {
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				log.debug("call . . .");
				Socket toServerSocket = new Socket("localhost", 1111);
				ObjectOutputStream out = new ObjectOutputStream(toServerSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(toServerSocket.getInputStream());
				out.writeObject(name);
				
				String serverMsg = String.valueOf(in.readObject());
				log.debug(serverMsg);
				toServerSocket.close();
				return serverMsg;
			}
		};
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
