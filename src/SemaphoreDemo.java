import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader {

	INSTANCE;
	
	private Semaphore semaphore = new Semaphore(3, true);
	
	public void downloadData() {
		try {
			semaphore.acquire();
			//System.err.println("Semaphore acquire");
			download();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		//	System.err.println("Semaphore Release");
			semaphore.release();
		}
		
	
		
	}

	private void download() {
		System.out.println("Download files");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}

public class SemaphoreDemo {

	public static void main(String[] args) {
	 ExecutorService executorService = Executors.newCachedThreadPool();
	  for (int i = 0; i < 12; i++) {
		executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				Downloader.INSTANCE.downloadData();
				
			}
		});
	}
	}

}
