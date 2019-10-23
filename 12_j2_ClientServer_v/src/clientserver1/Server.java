package clientserver1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public Server() throws IOException {
		ServerSocket serverSocket = new ServerSocket(1234);
		while (true) {
			System.out.println("Server wartet . . .");
			Socket toClientSocket = serverSocket.accept(); // Server wartet mit accept
			InputStream in = toClientSocket.getInputStream();
			OutputStream out = toClientSocket.getOutputStream();
			// Protokoll
			int a = in.read();
			int b = in.read();
			out.write(a + b);
		}
		
		
//		serverSocket.close();
	}
	
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
