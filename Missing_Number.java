// https://leetcode.com/problems/missing-number/

import java.util.Arrays;
import java.util.HashSet;

// Brute Force
// For every value in 0..n, scan the array looking for it
// Time Complexity: O(n^2) - a linear scan for each candidate
// Space Complexity: O(1) - no extra space used

class Solution {
    public int missingNumber(int[] nums) {

        int n = nums.length;

        for (int candidate = 0; candidate <= n; candidate++) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (nums[i] == candidate) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return candidate;
            }
        }
        return -1;
    }
}

// Sorting Solution
// After sorting, nums[i] should equal i - the first mismatch is the answer
// Time Complexity: O(n log n) - sorting dominates
// Space Complexity: O(1) - sorted in place (ignoring sort's internal cost)

class Solution2 {
    public int missingNumber(int[] nums) {

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }

        // No mismatch found, so n itself is missing
        return nums.length;
    }
}

// HashSet Solution
// Time Complexity: O(n) - build the set, then one pass over 0..n
// Space Complexity: O(n) - set stores n elements

class Solution3 {
    public int missingNumber(int[] nums) {

        HashSet<Integer> seen = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            seen.add(nums[i]);
        }

        for (int candidate = 0; candidate <= nums.length; candidate++) {
            if (!seen.contains(candidate)) {
                return candidate;
            }
        }
        return -1;
    }
}

// Optimal Solution - Sum Formula
// Sum of 0..n is n * (n + 1) / 2, the gap is the missing number
// Time Complexity: O(n) - single pass over the array
// Space Complexity: O(1) - constant extra space

class Solution4 {
    public int missingNumber(int[] nums) {

        int n = nums.length;
        int expectedSum = n * (n + 1) / 2;

        int actualSum = 0;
        for (int i = 0; i < n; i++) {
            actualSum += nums[i];
        }

        return expectedSum - actualSum;
    }
}

// Optimal Solution - XOR
// Every index/value pair cancels out, leaving only the missing number
// Time Complexity: O(n) - single pass over the array
// Space Complexity: O(1) - constant extra space

class Solution5 {
    public int missingNumber(int[] nums) {

        int result = nums.length;

        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }

        return result;
    }
}
