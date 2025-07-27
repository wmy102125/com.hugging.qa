package com.hugging.qa.chat;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * 11. 盛最多水的容器
 * 已解答
 * 中等
 * 相关标签
 * premium lock icon
 * 相关企业
 * 提示
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 * <p>
 * 说明：你不能倾斜容器。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 * <p>
 * 输入：height = [1,1]
 * 输出：1
 */
public class MaxArea {
    public int maxArea(int[] height) {

        int maxArea = 0;
        int n = height.length;
        int secondPoint = n - 1;
        int i = 0;
        while (i < secondPoint) {
            int area = (Math.min(height[i], height[secondPoint])) * (secondPoint - i);
            maxArea = Math.max(maxArea, area);
            if (height[i] <= height[secondPoint]) {
                i++;
            }else if (height[i] > height[secondPoint]) {
                secondPoint--;
            }

        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,1};
        System.out.println(new MaxArea().maxArea(height));
    }
}
