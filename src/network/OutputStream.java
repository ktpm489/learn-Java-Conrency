package network;

import java.io.FileOutputStream;

public class OutputStream {

	public static void main(String[] args) {
		try {
			FileOutputStream fileOutput = new FileOutputStream("example2.txt");
			char H = 'H';
			fileOutput.write((char)H);
			String str = "Hello Java Network Programming";
			fileOutput.write(str.getBytes());
			fileOutput.close();
		}catch (Exception e) {
			
		}

	}

}
