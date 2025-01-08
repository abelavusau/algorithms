package com.abelavusau.algorithms.leetcode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 * 26. Remove Duplicates from Sorted Array
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int r = nums.length;
        int l = 0;

        while (l < r) {
            int k = l + 1;

            if (k == r) return r;

            if (nums[l] == nums[k]) {
                while (k < r) {
                    nums[k - 1] = nums[k];
                    k++;
                }

                l = 0;
                r--;
            } else {
                l++;
            }
        }

        return r;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,2}; // Input array
        int[] expectedNums = {1,2}; // The expected answer with correct length

//        int[] nums = {0,0,1,1,1,2,2,3,3,4}; // Input array
//        int[] expectedNums = {0,1,2,3,4}; // The expected answer with correct length

//        int[] nums = {1}; // Input array
//        int[] expectedNums = {1}; // The expected answer with correct length

        RemoveDuplicates removeDuplicates = new RemoveDuplicates();

        int k = removeDuplicates.removeDuplicates(nums); // Calls your implementation

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }
}
