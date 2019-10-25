package server;

import java.io.ObjectInputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.MessageObject;
/**
 * -Teil des Servers
 * @author Student
 *
 */
public class MessageService extends Service<MessageObject>{
	private static Logger log = LogManager.getLogger();
	private Socket toClientSocket;

	@Override
	protected Task<MessageObject> createTask() {
		return new Task<MessageObject>(){

			
			
			@Override
			protected MessageObject call() throws Exception {
				log.info("call");
				
				ObjectInputStream in = new ObjectInputStream(toClientSocket.getInputStream());
				MessageObject msgo =  (MessageObject) in.readObject();
				msgo.setResponseObject(true);
				log.info("read from client "+msgo);
				return msgo;
			}
			
		};
	}

	public void setSocket(Socket toClientSocket) {
		this.toClientSocket = toClientSocket;
		
	}

	

}
