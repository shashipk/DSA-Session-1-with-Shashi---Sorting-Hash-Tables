// https://leetcode.com/problems/contains-duplicate/

import java.util.Arrays;
import java.util.HashSet;

// Brute Force
// Time Complexity: O(n^2) - compare every pair of elements
// Space Complexity: O(1) - no extra space used

class Solution {
    public boolean containsDuplicate(int[] nums) {

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}

// Sorting Solution
// Duplicates become neighbours once the array is sorted
// Time Complexity: O(n log n) - sorting dominates
// Space Complexity: O(1) - sorted in place (ignoring sort's internal cost)

class Solution2 {
    public boolean containsDuplicate(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}

// Optimal Solution - HashSet
// Time Complexity: O(n) - single pass over the array
// Space Complexity: O(n) - set stores up to n elements

class Solution3 {
    public boolean containsDuplicate(int[] nums) {

        HashSet<Integer> seen = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (seen.contains(nums[i])) {
                return true;
            }
            seen.add(nums[i]);
        }

        return false;
    }
}
