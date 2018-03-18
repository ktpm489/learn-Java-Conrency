package network;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharacterStream {

	public static void main(String[] args) {
		// Method write and read character input output stream
		try {
			
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("example4.txt"));
			InputStreamReader in = new InputStreamReader(new FileInputStream("example4.txt"));
			System.out.println(out.getEncoding());
			out.write("Hello word");
			out.flush();
			out.write("Another String");
			out.flush();
			out.close();
			// read string
			int data = in.read();
			while (data != -1) {
				System.out.println((char)data);
				data = in.read();
			}
		}catch (Exception e) {
			
		}
		

	}

}
