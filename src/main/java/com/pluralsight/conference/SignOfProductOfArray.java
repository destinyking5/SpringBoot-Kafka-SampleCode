package com.pluralsight.conference;

public class SignOfProductOfArray {
    public static void main(String[] args) {
        int[] nums = {-1, -2, -3, -4, 3, 2, 1}; //4 negatives
        System.out.println(SignOfProductOfArray.arraySign(nums));

        int[] nums2 = {-1, -2, -3, -4, 3, 2, 1, -1}; //5 negatives
        System.out.println(SignOfProductOfArray.arraySign(nums2));

        int[] nums3 = {-1, -2, -3, -4, 3, 2, 1, 0}; //one 0
        System.out.println(SignOfProductOfArray.arraySign(nums3));
    }

    public static int arraySign(int[] nums) {
        int negCount = 0;
        for (int num : nums) {
            if (num == 0) return 0;
            else if (num < 0) negCount++;
        }
        if (negCount % 2 == 1) return -1;
        return 1;
    }
}
