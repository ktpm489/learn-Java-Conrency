package network;
import java.io.*;

public class InputStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		FileInputStream inputStream = new FileInputStream("InputStream.txt");
		int data = inputStream.read();
		while (data != -1) {
			System.out.println((char)data);
			data = inputStream.read();
		}
		inputStream.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
