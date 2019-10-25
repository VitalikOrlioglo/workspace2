package service;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * - ServerSocket an Port binden z.B. 1111
 * - warten mit accept
 * - MessageService starten
 * - Membervar ?
 * 
 * Student
 * @author vitali orlioglo
 * 24.10.2019
 * A07_fx_TimeServer01_v
 */
public class ServerService extends Service<String> {
	private static final int SERVER_PORT = 1111;

	private static Logger log = LogManager.getRootLogger();
	
	private ServerSocket serverSocket;
	private MessageService messageService = new MessageService();
	
	@Override
	protected Task<String> createTask() {//3.
		return new Task<String>() {
			
			@Override
			protected String call() throws Exception { //4.
				log.debug("call . . .");
				//7.
				serverSocket = new ServerSocket(SERVER_PORT);
				updateMessage("Server start am Port " + serverSocket.getLocalPort());
				
				while (true) {
					log.debug("Server {} wartet mit accept ", serverSocket.getLocalPort());
					Socket toClientSocket = serverSocket.accept();
					log.debug("Server wartet mit accept {} ", toClientSocket.getInetAddress());
					
					//8.
					messageService.setupStreams(toClientSocket);
					Platform.runLater(() -> {
						messageService.restart();
					} );
					
//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							messageService.restart();
//						}
//					});
				}
				//return null;
			}
		};
	}
}
