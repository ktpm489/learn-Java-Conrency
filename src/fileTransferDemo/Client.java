package fileTransferDemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	public static void main(String[] args) {
		try {

			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(in);
			String ipAddress = "";
			String fileName = "";
			boolean isValid = false;
			while (!isValid) {
				
				System.out.println("Please enter a valid Server Ip Address:");
				// read the entered ip address
				ipAddress = reader.readLine();
				isValid = validateIpAddress(ipAddress);
			}
			
			System.out.println("Please enter a file name");
			fileName = reader.readLine();
			
			Socket socket = new Socket(ipAddress ,9090);
			InputStream inputBytes = socket.getInputStream();
			BufferedInputStream input = new BufferedInputStream(inputBytes);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			// send desired filename
			out.println(fileName);
			
			int code = input.read();
			
			if (code == 1) {
				BufferedOutputStream outputFile = new BufferedOutputStream( new FileOutputStream("D:\\TrainingWeb\\" + fileName));
				byte[] buffer = new byte[1024];
				int bytesRead = 0;
				while ((bytesRead = input.read(buffer)) != -1) {
					System.out.println("."); // acts as a download indicator
					outputFile.write(buffer,0, bytesRead);
					outputFile.flush();
				}
				outputFile.close();
				input.close();
				System.out.println();
				System.out.println("File :" + fileName + " was succesfully downloaded");
			} else {
				System.out.println("File is not present on the server");
			}
			socket.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
	
	public static  boolean validateIpAddress(String ipAddress) {
		String[] numbers = ipAddress.split("\\.");
		if (numbers.length !=4) {
			return false;
		}
		for (String string : numbers) {
			int i = Integer.parseInt(string);
			if (i< 0 || i> 255) {
				return false;
			}
		}
		return true;
	}

}
