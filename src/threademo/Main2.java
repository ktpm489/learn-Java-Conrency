package threademo;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int second = 1;
		try {
			while (second < 100) {
				Thread.sleep(1000);
				System.err.println("Second: "+ second);
				second++;
			}
		} catch(Exception e) {
			
		}
	}

}
