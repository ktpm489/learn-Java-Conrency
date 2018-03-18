package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BufferStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader reader = new BufferedReader(new FileReader("example6.txt"));
			BufferedWriter writer = new BufferedWriter(new FileWriter("example7.txt"));
			String line = null;
			while (( line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
				
			}
			writer.close();
		}catch(Exception e) {
			
		}
	}

}
