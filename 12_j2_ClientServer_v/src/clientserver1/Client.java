package clientserver1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public Client() throws UnknownHostException, IOException {
		Socket toServerSocket = new Socket("localhost", 1234);
		InputStream in = toServerSocket.getInputStream();
		OutputStream out = toServerSocket.getOutputStream();
		out.write(3);
		out.write(4);
		
		System.out.println(in.read());
		
		toServerSocket.close();
	}
	
	public static void main(String[] args) {
		try {
			new Client();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
