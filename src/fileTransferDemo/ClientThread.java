package fileTransferDemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread{
	
	private Socket socket;
	private BufferedReader reader;
	private BufferedOutputStream out;
	private BufferedInputStream fileReader;
	
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
		
		try {
			// create the buffered reader
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// create the output buffered reader
			out = new BufferedOutputStream(socket.getOutputStream());
			
			// read the file name
			String fileName = reader.readLine();
			System.out.println("file name:" + fileName + " has been requested by "+ socket.getInetAddress().getHostAddress());
			// load file
			File file = new File(fileName);
			if (!file.exists()) {
				// if file does not exists send code O and close the connection
				byte code = (byte)0;
				out.write(code);
				closeConnection();
			} else {
				// if the file exists send code 1 and send the file
				out.write((byte)1);
				// create a buffered input stream variable
				fileReader = new BufferedInputStream(new FileInputStream(file));
				// set the buffer size
				byte[] buffer = new byte[1024];
				// this integer is store the 
				int bytesRead = 0;
				while ((bytesRead = fileReader.read(buffer)) != -1) {
					out.write(buffer,0, bytesRead);
					out.flush();
				}
				// close the connection after the download is finished
				closeConnection();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void closeConnection() {
		try {
		if (out!=null) {
			out.close();
			socket.close();
		}
		if (reader!= null) {
			reader.close();
		}
		if (fileReader !=null) {
			fileReader.close();
		}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
}
