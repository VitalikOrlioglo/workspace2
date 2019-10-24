package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * - Name / Message vom Client lesen - Serverzeit ermitteln - Name/Message +
 * Serverzeit zurück senden cimadata Java 2
 * 
 * @author micha schirmer 24.10.2019 A07_fx_TimeServer0.1
 *
 */

public class MessageService extends Service<String> {
	private static Logger log = LogManager.getLogger();
	private ObjectInputStream in;
	private ObjectOutputStream out;

	@Override
	protected Task<String> createTask() {
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				log.debug("call...");
				String name = String.valueOf(in.readObject());
				log.debug("Name vom Client {} ",name);
				String msg = name + " "+LocalTime.now();
				log.debug("Message {} ",msg);
				out.writeObject(msg);
				return msg;
			}
		};
	}

	// FIXME impl
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
