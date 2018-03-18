package smtpDemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EmailSocket {
	
	private static Socket smtpSocket;
	private static PrintWriter out;
	private static BufferedReader in;
	
	public static void main(String[] args) {
		// Initialization section:
		// Try open a socket on port 25
		// Try open input and output streams
		try {
			smtpSocket = new Socket("localhost", 25);
			in = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
			out= new PrintWriter(smtpSocket.getOutputStream(), true);
		} catch( UnknownHostException e) {
			System.out.println("Don't know about host: host name");
		} catch (IOException e) {
			System.out.println("Could't get I/O for the connection to : host name");
		}
		
		// If every thing has been initalized then we want to write some data to the socket we have opened a connection to on port 25
		if (smtpSocket != null && out !=null && in != null) {
			try {
				// Step 1: Get a greeting by the server
				String responseLine;
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server " + responseLine);
					if (responseLine.indexOf("220") != -1) {
						break;
					}
				}
				// Step 2 The client initiates its dialog by responding with a HELO command identifying itself 
				//out.println("Hello " + InetAddress.getLocalHost().getHostAddress());
				System.out.println("Hello " + InetAddress.getLocalHost().getHostAddress() );
				while ((responseLine = in.readLine()) !=null) {
					System.out.println("Server: "+ responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}
				
				// Step 3: The client notifies the receiver of the originating email address of the message in a MAIL FROM command.
				out.println("MAIL from: mytest@test.com");
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}
				
				// Step 4: The client notifies the receiver of the recipient email address of the message in a RCPT TO command.
				out.println("RCPT TO: lapnghiep1368@gmail.com");
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("250") != -1) {
						break;
					}
				}
				
				// Step 5: Send DATA command
				out.println("DATA");
				while ((responseLine = in.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("354") != -1) {
						break;
					}
				}
				
				// Step 6: Send Email Body
				 out.println("From: mytest@test.com");
				 out.println("To: hoangvuit893@gmail.com");
				 out.println("Subject: TEST EMAIL");
				 out.println();
				 out.println("Subject: TEST EMAIL"); // message body
				 out.println("This is a test message"); // message body
				 out.println("Thanks");
				 out.println("Java Network Programming course");
				 out.println();
				 out.println(".");
				 // Step 7: Send Quit Command
				 while ((responseLine = in.readLine()) != null) {
						System.out.println("Server: " + responseLine);
						if (responseLine.indexOf("250") != -1) {
							break;
						}
					}
				 
				 System.out.println("Email successfully sent !");

				 out.close();
				 in.close();
				 smtpSocket.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}

}
