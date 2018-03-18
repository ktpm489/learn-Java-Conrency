package fileTransferDemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		try {
			ServerSocket serverSocket = new ServerSocket(9090);
			// boolean variable to stop the server
			boolean isStopped = false;
			if (!isStopped) {
				// create clinent socket object
				Socket clientSocket = serverSocket.accept();
				// create and start client thread
				ClientThread clientThread = new ClientThread(clientSocket);
				clientThread.start();
			}
			serverSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
