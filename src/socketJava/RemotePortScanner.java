package socketJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class RemotePortScanner {

	public static void main(String[] args) {
		
		InputStreamReader in = new InputStreamReader(System.in);

		BufferedReader reader = new BufferedReader(in);
		String targetIp = "";
		int fromPort = 0;
		int toPort = 0;
		System.out.println("Please enter the target ip address:");
		try {
			targetIp = reader.readLine();
		}catch (Exception e) {
			System.out.println(" Can not read the ip address"  + e.toString());
		}
		// boolean value which is used to control the validation process
		boolean isValid = false;
		while (!isValid) {
			try {
				System.out.println(" Please enter the  firtst port number: ");
				String portString = reader.readLine();
				fromPort = Integer.parseInt(portString);
				if ( fromPort >= 0  && fromPort <= 65536) {
				System.out.println(" Port is accepted");
				isValid = true;
				} else {
					System.out.println("Invalid port! Port range is: 0 - 65536");
				}
			} catch (Exception e) {
				System.out.println("Please insert a number");
				System.out.println("Please enter the port number");
			}
		}
		
		isValid = false; // reinitialize the boolean value to false in order to start  a new validation state
				
		while (!isValid) {
			try {
				System.out.println(" Please enter the  last port number: ");
				String portString = reader.readLine();
				toPort = Integer.parseInt(portString);
				if ( fromPort >= 0  && fromPort <= 65536 && toPort > fromPort) {
				System.out.println(" Port is accepted");
				isValid = true;
				} else {
					System.out.println("Invalid port! Port range is: 0 - 65536");
				}
			} catch (Exception e) {
				System.out.println("Please insert a number");
				System.out.println("Please enter the port number");
			}
		}
		
		/// start create socket
		int port = fromPort;
		while (port >= fromPort && port <=toPort) {

			try {
				Socket socket = new Socket(targetIp, port);
				System.out.println("Port" + port + " is in listening state!");
				socket.close();
			} catch(UnknownHostException e1) //catch block is executed if an invalid host was entered
            {
                System.out.println("Unknown host exception " + e1.toString());
            }
            catch(IOException e2) //execute when the port is already opened
            {
                System.out.println("Port " + port + " is not opened!"); // print what port is opened
            }
            catch(Exception e) //execute if an other exception is raised
            {
                System.out.println(e.toString()); //print the error
            }
			port++; //increment port number in order to test the next port
		}
	}

}
