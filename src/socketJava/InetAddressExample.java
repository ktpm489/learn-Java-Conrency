package socketJava;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {

	public static void main(String[] args) {
		try {
			InetAddress address =  InetAddress.getLocalHost();
			System.out.println(address.getHostName());
			System.out.println(address.getHostAddress());
			InetAddress address2 =  InetAddress.getByName("google.com");
			System.out.println(address2.getHostName());
			System.out.println(address2.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
