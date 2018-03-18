package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SystemInDemo {

	public static void main(String[] args) {

		System.out.println(" Please enter the port number: ");
		InputStreamReader in = new InputStreamReader(System.in);

		BufferedReader reader = new BufferedReader(in);
		boolean isValid = false;
		int port = 0;

		while (!isValid) {
			try {
				String portString = reader.readLine();
				port = Integer.parseInt(portString);
				System.out.println(" Port is accepted");
				isValid = true;
			} catch (Exception e) {
				System.out.println("Please insert a number");
				System.out.println("Please enter the port number");
			}
		}
		System.out.println(" Please enter server IP address");
		String ipAddress = "";
		try {
			ipAddress = reader.readLine();
		} catch (Exception e) {
			System.out.println("Cannot read the ip address" + e.toString());
		}
		System.out.println("port" +  port + "idAddress" + ipAddress);
	}

}
