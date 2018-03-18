package network;

import java.io.PrintStream;

public class PriStream {

	public static void main(String[] args) {
	
		try {
			PrintStream out = new PrintStream("example8.txt");
			int var1= 30;
			System.out.println("The value of va1" + var1);
			out.println(var1);
			out.close();
		}catch(Exception e) {
			
		}

	}

}
