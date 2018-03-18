package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) {
	
		try {
			DatagramSocket clientSocket = new DatagramSocket(0);
			byte[] sendData = new byte[1024]; // store outgoing data
			byte[] receiveData = new byte[1024]; // store incoming data
			// Amount of data =65535- 20 (IP Header) - 8 (UDP header) = 65508
			InetAddress serverAddress = InetAddress.getByName("localhost");
			String stringSendData = "Hello Server";
			sendData =stringSendData.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,serverAddress, 9090);
			clientSocket.send(sendPacket);
			DatagramPacket receivePacker= new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacker);
			receiveData = receivePacker.getData();
			String stringReceiveData = new String(receiveData);
			System.out.println("FROM SERVER" + stringReceiveData);
			clientSocket.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
