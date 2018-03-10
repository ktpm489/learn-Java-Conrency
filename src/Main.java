
public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
			Demo demo = new Demo();
			Runnable a = () -> demo.a();
			Runnable b = () -> demo.b();
			
			Thread t1 = new Thread(a);
			Thread t2 = new Thread(b);
			t1.start();
			t2.start();
			t1.join();
			t2.join();
	}

}
