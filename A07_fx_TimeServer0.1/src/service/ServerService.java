package service;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.concurrent.Service;
import javafx.concurrent.Task;


/**
 * - ServerSocket an Port binden z.B.1111
 * - warten mit accept
 * - MessageService starten
 * - Membervar?
 * cimadata Java 2
 * @author micha schirmer 
 * 24.10.2019
 * A07_fx_TimeServer0.1
 *
 */
public class ServerService extends Service<String> {
	private static Logger log  = LogManager.getLogger();
	
	private ServerSocket serverSocket;
	private MessageService messageService = new MessageService();

	@Override
	protected Task<String> createTask() {
		return new Task<String>() {
			

			@Override
			protected String call() throws Exception {
				log.debug("call..");
				serverSocket = new ServerSocket(1111);
				updateMessage("Server start am Port "+serverSocket.getLocalPort());
				while(true) {
					log.debug("Server {} wartet mit accept ",serverSocket.getLocalPort() );
					Socket  toClientSocket = serverSocket.accept();
					updateValue(""+toClientSocket);
					log.debug("Server  wartet mit accept {}",toClientSocket.getInetAddress());
					
					messageService.setupStreams(toClientSocket);
					messageService.restart();
				}
				
				
				//return null;
			}
		};
	}

}
