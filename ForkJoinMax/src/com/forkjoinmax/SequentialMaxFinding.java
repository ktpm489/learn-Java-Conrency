package com.forkjoinmax;

import java.util.Iterator;

public class SequentialMaxFinding {

	public int sequentialMaxFind(int[] nums, int highIndex) {
		int max = nums[0];
		for (int i = 0; i < highIndex; i++) {
			if (nums[i] > max) {
				max = nums[i];
			}
		}
		return max;
	}
}

