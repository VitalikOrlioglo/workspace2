package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * - Name / Message vom Client lesen
 * - Serverzeit ermitteln
 * - Name / Message + Serverzeit zuruck senden
 * Student
 * @author vitali orlioglo
 * 24.10.2019
 * A07_fx_TimeServer01_v
 */
public class MessageService extends Service<String>{
	private static Logger log = LogManager.getRootLogger();
	private ObjectInputStream in;
	private ObjectOutputStream out;

	@Override
	protected Task<String> createTask() {
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				log.debug("call . . .");
				
				//12.
				String name = String.valueOf(in.readObject());
				log.debug("Name vom Client {}", name);
				String msg = name + "" + LocalTime.now();
				log.debug("Message {} ", msg);
				out.writeObject(msg);//13.
				return msg;// wird eigentlich nicht benotigt TODO Server zeigt Message an
			}
		};
	}
	
	//9. -> dann Client implementieren
	public void setupStreams(Socket toClientSocket) {
		log.debug(toClientSocket);
		try {
			in = new ObjectInputStream(toClientSocket.getInputStream());
			out = new ObjectOutputStream(toClientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
