package demoLib;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Delay Queue keeps the elements internally until a certain delay has expired
 * 	
 * size() return the count of both expired and unexpired items
 * 
 * */
public class DelayQueueExample {

	public static void main(String[] args) {
		BlockingQueue<DelayedWorker> queue = new DelayQueue<>();
		try {
			queue.put(new DelayedWorker(1000, "This is the .1. message..."));
			queue.put(new DelayedWorker(2000, "This is the .2. message..."));
			queue.put(new DelayedWorker(3000, "This is the .3. message..."));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while  (!queue.isEmpty()) {
			try {
				System.err.println(queue.take().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

class DelayedWorker implements Delayed{
	private long duration;
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DelayedWorker(long duration ,String message) {
		this.duration = System.currentTimeMillis() + duration;
		this.message = message;
	}
	
	@Override
	public int compareTo(Delayed otherDelayed) {
		if (this.duration  < ((DelayedWorker)otherDelayed).getDuration()) {
			return -1;
		}
		
		if (this.duration  > ((DelayedWorker)otherDelayed).getDuration()) {
			return 1;
		}
		return 0;
		
	}

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return  unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}
	
	@Override
	public String toString() {
		return this.message;
	}
	
}
