package com.abelavusau.algorithms.leetcode;

import java.util.Arrays;

/**
 * 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class MergeSortedArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;

        while(i < m + n && j < n) {
            if (nums1[i] >= nums2[j]) {
                shift(nums1, i);
                nums1[i] = nums2[j];
                j++;
            }

            i++;
        }

        i = m + j;

        while (j < n) {
            nums1[i++] = nums2[j++];
        }
    }

    private static void shift(int[] nums1, int pos) {
        for (int i = nums1.length - 1; i > pos; i--) {
            nums1[i] = nums1[i - 1];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {10,12,13,0,0,0,0,0}, nums2 = {2,5,6,7,8};
        int m = 3, n = 5;
//
//        int[] nums1 = {1}, nums2 = {};
//        int m = 1, n = 0;

//        int[] nums1 = {0}, nums2 = {1};
//        int m = 0, n = 1;

//        int[] nums1 = {1,2,3,0,0,0}, nums2 = {2,5,6};
//        int m = 3, n = 3;

        MergeSortedArrays obj = new MergeSortedArrays();
        obj.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
