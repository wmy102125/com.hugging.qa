package com.hugging.qa.chat;

import java.util.*;

public class TwoIntIndex {
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

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs.length == 0) {
            return result;
        }
        if (strs.length == 1) {
            result.add(Arrays.asList(strs[0]));
            return result;
        }

        //translate the element to int value
        List<String> strList = Arrays.asList(strs);
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strList.size(); i++) {
            char[] array = strList.get(i).toCharArray();
            int[] intArray = new int[26];
            for (int j = 0; j < array.length; j++) {
                int value = array[j];
                intArray[value - 'a']++;
            }
            StringBuffer key = new StringBuffer();
            for (int y = 0; y < intArray.length; y++) {
                if (intArray[y] != 0) {
                    key.append((char) ('a' + y)).append(intArray[y]).append("#");
                }
            }
            map.computeIfAbsent(key.toString(), keySet -> new ArrayList<>()).add(strList.get(i));
        }

        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public List<List<String>> goupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs.length == 0) {
            return result;
        }
        if (strs.length == 1) {
            result.add(Arrays.asList(strs[0]));
            return result;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            list.toArray();

        }
        for (int i = 0; i < strs.length; i++) {
            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            String keyStr = new String(charArray);
            map.computeIfAbsent(keyStr, key -> new ArrayList<>()).add(strs[i]);
        }

        return new ArrayList<>(map.values());
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int left = 0, right = 0;
        int length = 0;
        for (int now : numSet) {
            left = now - 1;
            right = now + 1;
            //只从起点遍历
            if (!numSet.contains(left)) {
                // int cursionLeft = leftCursion(left, numSet);
                int cursionRight = rightCursion(right, numSet);
                length = Math.max(length, cursionRight + 1);
            }

        }
        return length;

    }

    public int leftCursion(int left, Set<Integer> numSet) {
        int length = 0;
        int finalLeft = left;
        if (numSet.contains(left)) {
            length++;
            left--;
            return length + leftCursion(left, numSet);
        }
        return length;
    }

    public int rightCursion(int right, Set<Integer> numSet) {
        int length = 0;
        int finalRight = right;
        if (numSet.contains(right)) {
            length++;
            right++;
            return length + rightCursion(right, numSet);
        }
        return length;
    }

    public void moveZeroes(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return;
        }
        int zeroIndex = 0;
        int otherIndex = 0;
        while (otherIndex < nums.length && zeroIndex < nums.length) {
            if (nums[zeroIndex] == 0 && nums[otherIndex] != 0) {

                if (otherIndex < zeroIndex) {
                    otherIndex++;
                    continue;
                }
                int temp = nums[zeroIndex];
                nums[zeroIndex] = nums[otherIndex];
                nums[otherIndex] = temp;
                zeroIndex++;
                otherIndex++;
                continue;
            }
            if (nums[zeroIndex] != 0) {
                zeroIndex++;
            }

            if (nums[otherIndex] == 0) {
                otherIndex++;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                int temp = nums[fast];
                nums[fast] = nums[slow];
                nums[slow] = temp;
                slow++;
            }
            fast++;
        }
    }

    public static void main(String[] args) {
        int[] twonums = new int[]{1,11, 15,2, 7};
        int target = 9;
        System.out.println(new TwoIntIndex().twoSum3(twonums, target));

        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(new TwoIntIndex().longestConsecutive(nums));

        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new TwoIntIndex().goupAnagrams2(strs));

        int[] zeroesNums = new int[]{0, 1, 0, 3, 12};
        new TwoIntIndex().moveZeroes2(zeroesNums);

    }
}
