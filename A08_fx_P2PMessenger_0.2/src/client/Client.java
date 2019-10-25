package client;

import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.MessageObject;

public class Client extends Service<String> {
	private static Logger log = LogManager.getLogger();
	
	
	private String serverHost;


	private MessageObject messageObject;

	@Override
	protected Task<String> createTask() {
		return new Task<String>() {

			@Override
			protected String call() throws Exception {
				log.info("call");
				Socket toServerSocket = new Socket(serverHost,1111);
				ObjectOutputStream out = new ObjectOutputStream(toServerSocket.getOutputStream());
				out.writeObject(messageObject);
				log.debug("write Message "+messageObject);
				toServerSocket.close();
				return messageObject+"";
			}
		};
	}


	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
		
	}


	public void setMessageObject(MessageObject msgo) {
		this.messageObject = msgo;
		
	}

}
