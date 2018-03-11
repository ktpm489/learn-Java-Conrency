package forkJoinFramework;

import java.util.concurrent.ForkJoinPool;

/**
 * 
 * fork() : asynchronously execute the given task in the pool  we can call this on RecursiveAction or RecuriveTask<T>
 * 
 * join(): returns the result of the computation when it is done
 * invoke() executed the given task + return its result upon completion
 * */
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(200);
		pool.invoke(simpleRecursiveAction);

	}

}
