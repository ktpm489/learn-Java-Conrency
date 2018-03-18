package runableimplements;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ImplementRunable runable1 = new ImplementRunable(1);
		Thread thread1 = new Thread(runable1);
		thread1.start();
		ImplementRunable runable2 = new ImplementRunable(2);
		Thread thread2 = new Thread(runable2);
		thread2.start();
		ImplementRunable runable3 = new ImplementRunable(3);
		runable3.start();
	}

}
