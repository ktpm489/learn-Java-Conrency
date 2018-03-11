import java.lang.reflect.Executable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  1. ExecutorService  executorService = Executors.newCachedThreadPool()
 *   return an executor Service that can dynamically reuse threads
 * 
 * 2. Executors.newFixedThreadPool(N)
 *  -> limit  threads
 * 
 * 3. Executors.newSingleThreadExecutor()
 * use a single thread for the job
 * execute() -> runable + callable
 * submit() -> runable
 * 
 * **/
public class ExecutoreServiceDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			/// Example use submit
		//ExecutorService  executorService = Executors.newFixedThreadPool(5);
		ExecutorService  executorService = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++) {
			executorService.submit(new Workers());
		}
	}

}
class Workers implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
