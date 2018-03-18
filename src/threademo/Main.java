package threademo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ServerThread thread = new ServerThread("Thread 1");
		/*ServerThread thread2 = new ServerThread("Thread 2");
		thread2.setPriority(Thread.MAX_PRIORITY);
		thread2.run();*/
		thread.run();
	}

}
