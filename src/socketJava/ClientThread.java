package socketJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	private Socket socket;
	
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		try {
			PrintWriter	out = new PrintWriter(socket.getOutputStream(),true);
			out.println("Hello client");
			// Listen data from client
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String clientInput = input.readLine();
			System.out.println(clientInput);
			out.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
	}
}
