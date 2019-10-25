package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Server extends Service<String>{
	private static Logger log = LogManager.getLogger();
	private ServerSocket serverSocket;
	private MessageService messageService;
	private String serverhost;
	
	
	public void setServerhost(String serverhost) {
		this.serverhost = serverhost;
	}

	public Server() {
		log.info("init ServerSocket/MessageService");
		try {
			serverSocket = new ServerSocket();
			//serverSocket = new ServerSocket(1111);
			log.warn("ServerSocket() wird später gebunden");
			messageService = new MessageService();//
		} catch (IOException e) {
			log.error(e.getStackTrace());
		}
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setService(MessageService service) {
		this.messageService = service;
	}



	@Override
	protected Task<String> createTask() {
		return new Task<String>() {
			@Override
			protected String call() throws Exception {		
				log.info("Server.call");
				log.debug("InetSocketAddress +"+serverhost);
				
				// bei realer Verteilung des Programs entfernen
				serverSocket.bind(new InetSocketAddress(serverhost, 1111));//127.0.0.3
				if(serverSocket.isBound()) {
					updateMessage("SERVER_BOUND");
				}
				while(true) {
					log.debug("while wartet mit accept");
					Socket toClientSocket = serverSocket.accept();
					log.debug("Socket vom Client "+toClientSocket);
					messageService.setSocket(toClientSocket);
					
					Platform.runLater( ()->{
						log.debug("Messageservice restart");
						messageService.restart();
					});
				}
				
				
				//return null;
			}
		};
	}

}
