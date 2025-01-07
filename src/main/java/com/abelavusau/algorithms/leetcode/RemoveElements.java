package com.abelavusau.algorithms.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150
 * 27. Remove Element
 */
public class RemoveElements {
    public int removeElement(int[] nums, int val) {
        for (int i = 0, j = nums.length - 1; i < nums.length; i++) {
            if (nums[i] == val) {
                while(nums[j] == val && j > i) {
                    j--;
                }

                if (nums[j] != val) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        return actualLength(nums, val);
    }

    public static int actualLength(int[] nums, int val) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                return i;
            }
        }

        return nums.length;
    }

    public static void main(String[] args) {
//        int[] nums = {2,2,3}; // Input array
//        int val = 2; // Value to remove
//        int[] expectedNums = {3}; // The expected answer with correct length.

//        int[] nums = {3,2,2,3}; // Input array
//        int val = 3; // Value to remove
//        int[] expectedNums = {2,2}; // The expected answer with correct length.

//        int[] nums = {0,1,2,2,3,0,4,2}; // Input array
//        int val = 2; // Value to remove
//        int[] expectedNums = {0,1,4,0,3}; // The expected answer with correct length.

        int[] nums = {3,2,2,3}; // Input array
        int val = 3; // Value to remove
        int[] expectedNums = {2,2}; // The expected answer with correct length.

        // It is sorted with no values equaling val.

        RemoveElements r = new RemoveElements();
        int k = r.removeElement(nums, val); // Calls your implementation

        assert k == expectedNums.length;
        Arrays.sort(nums, 0, k); // Sort the first k elements of nums
        for (int i = 0; i < actualLength(nums, val); i++) {
            assert nums[i] == expectedNums[i];
        }
    }
}
