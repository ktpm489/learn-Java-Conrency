package runableimplements;

public class ImplementRunable implements Runnable {

	private int threadIndex;
	public ImplementRunable(int index) {
		this.threadIndex = index;
	}
	
	@Override
	public void run() {
		try {
			int clientNumber = 1;
			while (clientNumber <= 100) {
				System.out.println("Runable" +  this.threadIndex  +"send data to client : " + clientNumber);

				Thread.sleep(1000);

				clientNumber++;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void start() {
		
		Thread thread = new Thread(this);
		thread.start();
	}

}
