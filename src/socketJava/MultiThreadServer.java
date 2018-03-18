package socketJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				try {
					ServerSocket serverSocket = new ServerSocket(9090);
					System.out.println("Waiting for clients....");
					boolean stop = false;
					while(!stop) {
					// Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
					Socket clientSocket = serverSocket.accept();
					System.out.println("Client is connected");
					ClientThread clientThread = new ClientThread(clientSocket);
					clientThread.start();
					// socket.close();
					}
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}

}
