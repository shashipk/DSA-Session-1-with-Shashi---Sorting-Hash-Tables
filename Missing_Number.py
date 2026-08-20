# https://leetcode.com/problems/missing-number/

# Brute Force
# For every value in 0..n, scan the array looking for it
# Time Complexity: O(n^2) - a linear scan for each candidate
# Space Complexity: O(1) - no extra space used

class Solution:
    def missingNumber(self, nums):
        n = len(nums)

        for candidate in range(n + 1):
            found = False

            for i in range(n):
                if nums[i] == candidate:
                    found = True
                    break

            if not found:
                return candidate
        return -1


# Sorting Solution
# After sorting, nums[i] should equal i - the first mismatch is the answer
# Time Complexity: O(n log n) - sorting dominates
# Space Complexity: O(1) - sorted in place (ignoring sort's internal cost)

class Solution2:
    def missingNumber(self, nums):
        nums.sort()

        for i in range(len(nums)):
            if nums[i] != i:
                return i

        # No mismatch found, so n itself is missing
        return len(nums)


# HashSet Solution
# Time Complexity: O(n) - build the set, then one pass over 0..n
# Space Complexity: O(n) - set stores n elements

class Solution3:
    def missingNumber(self, nums):
        seen = set(nums)

        for candidate in range(len(nums) + 1):
            if candidate not in seen:
                return candidate
        return -1


# Optimal Solution - Sum Formula
# Sum of 0..n is n * (n + 1) / 2, the gap is the missing number
# Time Complexity: O(n) - single pass over the array
# Space Complexity: O(1) - constant extra space

class Solution4:
    def missingNumber(self, nums):
        n = len(nums)
        expected_sum = n * (n + 1) // 2

        actual_sum = 0
        for num in nums:
            actual_sum += num

        return expected_sum - actual_sum


# Optimal Solution - XOR
# Every index/value pair cancels out, leaving only the missing number
# Time Complexity: O(n) - single pass over the array
# Space Complexity: O(1) - constant extra space

class Solution5:
    def missingNumber(self, nums):
        result = len(nums)

        for i in range(len(nums)):
            result ^= i ^ nums[i]

        return result
