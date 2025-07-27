package com.hugging.qa.chat;


import java.util.*;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 * <p>
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 * <p>
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 */
//[-4,-1,-1,0,1,2]
public class ThreeNumSum {
    public List<List<Integer>> threeSumNormal(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int mid = nums[i];
            if (i > 0 && mid == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = mid+nums[left]+nums[right];
                if(sum==0){
                    result.add(new ArrayList<>(Arrays.asList(mid,nums[left],nums[right])));
                   while(left<right&&nums[left]==nums[left+1]){
                       left++;
                   }
                    while(left<right&&nums[right]==nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }else if(sum>0){
                    //too big ,right--
                    right--;
                } else if (sum<0) {
                    //too small
                    left++;
                }
            }
        }
        return result;
    }




    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], key -> new ArrayList<>()).add(i);
        }

        for (int i = 0; i < nums.length; i++) {
            int first = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];
                int third = 0 - (first + second);
                if (map.containsKey(third)) {
                    List<Integer> indexList = map.get(third);
                    if (indexList == null) {
                        continue;
                    }
                    if (indexList.size() == 1) {
                        int thirdIndex = indexList.get(0);
                        if (thirdIndex != i && thirdIndex != j) {
                            List<Integer> subList = new ArrayList<>(Arrays.asList(first, second, third));
                            result.add(subList);
                        }


                    } else {
                        for (int m = 0; m < indexList.size(); m++) {
                            if (indexList.get(m) != i && indexList.get(m) != j) {
                                List<Integer> subList = new ArrayList<>(Arrays.asList(first, second, third));
                                result.add(subList);

                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int start = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int second = nums[j];
                int third = 0 - (second + start);
                if (map.containsKey(third)) {
                    List<Integer> thirdIndexs = map.get(third);

                    if (thirdIndexs.size() > 1) {
                        for (Integer index : thirdIndexs) {
                            if (index != i && index != j) {
                                List<Integer> subLsit = new ArrayList<>(Arrays.asList(start, second, third));
                                Collections.sort(subLsit);
                                if (!result.contains(subLsit)) {
                                    result.add(subLsit);
                                }
                                List<Integer> value = map.get(second);
                                if (value != null && !value.contains(j)) {
                                    map.computeIfAbsent(second, key -> new ArrayList<>()).add(j);
                                } else if (value == null) {
                                    map.computeIfAbsent(start, startKey -> new ArrayList<>()).add(i);

                                }

                            }
                        }

                    } else {
                        List<Integer> value = map.get(second);
                        if (value != null && !value.contains(j)) {
                            map.computeIfAbsent(second, key -> new ArrayList<>()).add(j);
                        } else if (value == null) {
                            map.computeIfAbsent(start, startKey -> new ArrayList<>()).add(i);

                        }
                    }
                } else {
                    List<Integer> startVluae = map.get(start);
                    if (startVluae != null && !startVluae.contains(i)) {
                        map.computeIfAbsent(start, startKey -> new ArrayList<>()).add(i);
                    } else if (startVluae == null) {
                        map.computeIfAbsent(start, startKey -> new ArrayList<>()).add(i);

                    }

                    List<Integer> value = map.get(second);
                    if (value != null && !value.contains(j)) {
                        map.computeIfAbsent(second, key -> new ArrayList<>()).add(j);
                    } else if (value == null) {
                        map.computeIfAbsent(start, startKey -> new ArrayList<>()).add(i);

                    }
                }
            }

        }
        return result;
    }

    //-1,0,1,2,-1,-4
    //-4,-1,-1,0,1,2
    public List<List<Integer>>threeSum3(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0 ; i <nums.length-1;i++){
            int mid = i+1 ;
            int second = nums.length-1 ;
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            while(mid<second){
                int sum = nums[mid]+nums[second]+nums[i];
                if(sum==0){
                    list.add(new ArrayList<>(Arrays.asList(nums[mid],nums[second],nums[i])));
                    while(mid<second&&nums[mid]==nums[mid+1]){
                        mid++;
                    }
                    while(mid<second&&nums[second]==nums[second-1]){
                        second--;
                    }
                    mid++;
                    second--;
                } else if (sum<0) {
                    mid++;
                }else{
                    second--;
                }
            }


        }



        return list;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
       // System.out.println(new ThreeNumSum().threeSumNormal(nums));
        System.out.println(new ThreeNumSum().threeSum3(nums));
    }
}
