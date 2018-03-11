package com.forkjoinmax;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static int THREASHOLD = 0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int[] nums = initializeNums();
			THREASHOLD = nums.length/Runtime.getRuntime().availableProcessors();
		SequentialMaxFinding sequentialMaxFinding = new SequentialMaxFinding();
		long start = System.currentTimeMillis();
		System.out.println("Max:" + sequentialMaxFinding.sequentialMaxFind(nums, nums.length));
		System.out.println("Time taken " + (System.currentTimeMillis() - start));
		
		System.out.println();
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ParallelMaxFinding parallelMaxFinding = new ParallelMaxFinding(nums, 0, nums.length);
		 start = System.currentTimeMillis();
		System.out.println("Max" + pool.invoke(parallelMaxFinding));
		System.out.println("Time taken " + (System.currentTimeMillis() - start));
	}
	private static int[] initializeNums() {
		Random random = new Random();
		int[] nums = new int[100000000];
		for (int i = 0 ; i < 100000000; i++) {
			nums[i] = random.nextInt(100000);
		}
		return nums;
	}

}
