package forkJoinFramework1;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask1 extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int simulatedWork;

	public SimpleRecursiveTask1(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}
	
	@Override
	protected Integer compute() {
		if (simulatedWork> 100) {
			System.err.println("Parallel execution needed becaue of the huge task..");
			SimpleRecursiveTask1 task1 = new SimpleRecursiveTask1(simulatedWork/2);
			SimpleRecursiveTask1 task2 = new SimpleRecursiveTask1(simulatedWork/2);
			task1.fork();
			task2.fork();
			int solution = 0;
			solution += task1.join();
			solution += task2.join();
			return solution;
		} else {
			System.err.println("No need to for parallel execution...");
			return 2*simulatedWork;
		}
		
	}

}
