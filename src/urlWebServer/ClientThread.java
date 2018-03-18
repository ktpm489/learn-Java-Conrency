package urlWebServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javax.xml.crypto.Data;

public class ClientThread extends Thread {

	private Socket socket;
	private boolean isStop;
	private BufferedReader in;
	private PrintWriter out;
	private File file;
	private static String CRLF = "\r\n";

	public ClientThread(Socket clientSocket) {
		this.socket = clientSocket;
		this.isStop = false;
	}

	public void run() {
		try {
			while (!isStop) {

				// create a buffer reader
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// create a PrintWriter
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				String line;
				String httpHeader = ""; // stores the html header
				String htmlFile = ""; // stores the requires html files
				while (true) {
					line = in.readLine(); // read each line
					if (line.equals(CRLF) || line.equals("")) // end of header
					{
						break;
					}
					httpHeader = httpHeader + line + "\n"; // add a new line to the header
					if (line.contains("GET")) // if linet contains get
					{
						// extract the html file name
						int beginIndex = line.indexOf("/");
						int endIndex = line.indexOf("HTTP");
						htmlFile = line.substring(beginIndex + 1, endIndex);

					}
				}
				System.out.println(httpHeader); // printhttpHeader
				proccessRequest(htmlFile); // process the request
				closeConnection(); // close the connection
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void proccessRequest(String htmlFile) throws IOException {
		File file = new File(htmlFile); // create a file variable
		if (file.exists()) {
			// create a BufferedReader to read the html file content
			BufferedReader reader = new BufferedReader(new FileReader(file));
			// send the HTTP head
			out.print("HTTP/1.0 200 OK" + CRLF);
			Date date = new Date();
			out.print("Date:" + date.toString() + CRLF);
			out.print("Server: java tiny web server" + CRLF);
			out.print("Content-Type: text/html" + CRLF);
			out.print("Content-Length: " + file.length() + CRLF);
			// end of http header
			String line = "";
			while ((line = reader.readLine()) != null) {
				out.println(line); // write the line to the socket
			}
			System.out.println("Break");
		} else { // if the file does not exists
			// send the HTTP head 404 not found
			out.print("HTTP/1.1 404 Not Found" + CRLF);
			Date date = new Date();
			out.print("Date :" + date.toString() + CRLF);
			out.print("Connection: close" + CRLF);
			out.println("Content-Type: text/html; charset=iso-8859-1" + CRLF);
			// end of http header

			// send file not found message
			out.println("<html><head>");
			out.println("<title>404 Not Found</title>");
			out.println("</head><body");
			out.println("<h1>Not Found</h1>");
			out.println("<p> The requested URL /" + htmlFile + "was not found in this server </p> ");
			out.println("</body></html>");
			out.print(CRLF);
		}

	}

	public void closeConnection() {

		try {
			out.close();
			in.close();
			socket.close();
			isStop = true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
