package com.hugging.qa.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoNumSumIndex {
    /**
     * answer
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(target - nums[i])) {
                map.put(nums[i], i);
            } else {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return new int[]{-1,-1};
    }
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //key is the nums value,index as the map's value
            map.computeIfAbsent(nums[i], key -> new ArrayList<>()).add(i);
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            List<Integer> indexList = entry.getValue();
            if (target - entry.getKey() == entry.getKey()) {
                result[0] = indexList.get(0);
                result[1] = indexList.get(1);
                return result;
            }
            if (map.containsKey(target - key)) {
                result[0] = indexList.get(0);
                result[1] = map.get(target - key).get(0);
                return result;
            }
        }
        return result;
    }

    public int[] twoSum(int[] nums, int target) {
        int start = 0, end = 0;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            start = nums[i];
            result[0] = start;
            int minus = target - start;
            if (containsMinus(minus, nums) != 0) {
                result[1] = containsMinus(minus, nums);
                return result;
            }
        }
        return result;
    }

    public static int containsMinus(int minus, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (minus == arr[i]) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] twonums = new int[]{1,11, 15,2, 7};
        int target = 9;
        System.out.println(new TwoNumSumIndex().twoSum3(twonums, target));


    }
}
