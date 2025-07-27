package com.hugging.qa.chat;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 128. 最长连续序列
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * 示例 3：
 * <p>
 * 输入：nums = [1,0,1,2]
 * 输出：3
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int length = 0;
        //排序过的列表了
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        for (Integer element : set) {
            int nowlenght =1;
            int left = element - 1;
            int right = element + 1;
            //contains left ，not the start
            if(!set.contains(left)){
                while (set.contains(right)) {
                    right++;
                    nowlenght++;
                }
            }

            length = Math.max(length, nowlenght);
        }
        return length;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(new LongestConsecutive().longestConsecutive(nums));
    }
}
