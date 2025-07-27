package com.hugging.qa.chat;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 代码
 * 测试用例
 * 测试结果
 * 测试结果
 * 42. 接雨水
 * 已解答
 * 困难
 * 相关标签
 * premium lock icon
 * 相关企业
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 */
public class Trap {
    public int trap(int[] height) {
        int sum = 0;

        Queue<Integer> queue = new ArrayDeque();
        for (int i = 0; i < height.length; i++) {
            if (i == 0 && height[i] == 0) {
                continue;
            }
            if (queue.size() == 0) {
                queue.offer(i);
                continue;
            }
            //[1,0] 2 【2,1,0,1,3】
            if (height[queue.peek()] <= height[i]) {
                queue.offer(i);
                //形成了一个凹地，可以计算水量了
                while (queue.size() > 1) {
                    int leftIndex = queue.poll();
                    sum += Math.abs(height[leftIndex] - height[queue.peek()]) * (queue.peek() - leftIndex);
                }
            } else if (queue.peek() > height[i]) {
                queue.offer(i);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new Trap().trap(height));
    }
}
