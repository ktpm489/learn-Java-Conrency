package threademo;

public class ServerThread extends Thread {

	public ServerThread(String threadName) {
		this.setName(threadName);
	}

	public void run() {
		try {
			int clientNumber = 1;
			while (clientNumber <= 100) {
				System.out.println("Server" + this.getName() + " send data to client : " + clientNumber);

				Thread.sleep(1000);

				clientNumber++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
