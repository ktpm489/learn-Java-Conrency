package forkJoinFramework1;

import java.util.concurrent.ForkJoinPool;

public class App1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveTask1 task = new SimpleRecursiveTask1(20000);
		System.out.println(pool.invoke(task));
	}

}
