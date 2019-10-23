package clientserver2;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public Client() throws UnknownHostException, IOException, ClassNotFoundException {
		Socket toServerSocket = new Socket("localhost", 1234);
		
		// auf Serverseite umgekehrte Reihenfolge der Objekt-Erzeugung
		ObjectOutputStream out = new ObjectOutputStream(toServerSocket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(toServerSocket.getInputStream());
		
		out.writeObject("Hallo Server . . .");
		
		System.out.println(in.readObject());
		toServerSocket.close();
	}
	
	public static void main(String[] args) {
		try {
			new Client();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
