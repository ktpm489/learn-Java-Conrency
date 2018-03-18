package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataStream {

	public static void main(String[] args) {

		File file = new File("example3.txt");

		if (file.exists()) {
			System.out.println("File already existed " + file.getName());
		} else {
			try {
				if (file.createNewFile()) {
					System.out.println("File was created");
					System.out.println("File path:" + file.getAbsolutePath());
				} else {
					System.out.println("Can't not create file");
				}
			}	catch (Exception e) {
				System.out.println(e.toString());
			}
			try {
				// Read data to new file is created
				DataOutputStream out = new DataOutputStream(new FileOutputStream(file.getName()));
				DataInputStream in = new DataInputStream(new FileInputStream(file.getName()));
				out.writeInt(32);
				out.writeDouble(678.000);
				int var1 = in.readInt();
				System.out.println( var1);
				in.close();
				out.close();
			}catch (Exception e) {
				
			}
		}
	}

}
