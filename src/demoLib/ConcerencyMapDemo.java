package demoLib;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.PriorityBlockingQueue;

class FirstWorket implements Runnable{

	private ConcurrentMap<String, Integer> map;
	
	public FirstWorket(ConcurrentMap<String, Integer> map) {
		this.map = map;
	}
	@Override
	public void run() {
		
		try {
			map.put("V",1);
			map.put("G",2);
			Thread.sleep(2000);
			map.put("K",3);
			Thread.sleep(1000);
			map.put("F",4);
			map.put("A",5);
			map.put("E",6);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}


class SecondWorket implements Runnable{

	private ConcurrentMap<String, Integer> map;
	public SecondWorket(ConcurrentMap<String, Integer> map) {
		this.map = map;
	}
	@Override
	public void run() {
		
		try {
			Thread.sleep(4000);
			System.err.println(map.get("A"));
			Thread.sleep(1000);
			
			System.err.println(map.get("G"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

public class ConcerencyMapDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcurrentMap<String, Integer> currentMap = new ConcurrentHashMap<>();
		new Thread(new FirstWorket(currentMap)).start();
		new Thread(new SecondWorket(currentMap)).start();
	}

}
