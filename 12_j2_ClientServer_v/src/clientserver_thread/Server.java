package clientserver_thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public Server() throws IOException, ClassNotFoundException {
		ServerSocket serverSocket = new ServerSocket(1234);
		while (true) {
			System.out.println("Server wartet . . .");
			Socket toClientSocket = serverSocket.accept(); //Server wartet mit accept
			
			handle(toClientSocket);
			
		}
		
		
//		serverSocket.close();
	}

	private void handle(Socket toClientSocket) throws IOException, ClassNotFoundException {
		
		Thread thread = new Thread( () -> {
			try {
				// auf Clientseite umgekehrte Reihenfolge der Objekt-Erzeugung
				ObjectInputStream in = new ObjectInputStream(toClientSocket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(toClientSocket.getOutputStream());
				// Protokoll
				String msg = String.valueOf(in.readObject());
				out.writeObject(msg.toLowerCase());
				} catch (Exception e) {
			}		
		});
		thread.start();
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
