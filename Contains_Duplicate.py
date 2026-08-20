# https://leetcode.com/problems/contains-duplicate/

# Brute Force
# Time Complexity: O(n^2) - compare every pair of elements
# Space Complexity: O(1) - no extra space used

class Solution:
    def containsDuplicate(self, nums):
        n = len(nums)

        for i in range(n):
            for j in range(i + 1, n):
                if nums[i] == nums[j]:
                    return True
        return False


# Sorting Solution
# Duplicates become neighbours once the array is sorted
# Time Complexity: O(n log n) - sorting dominates
# Space Complexity: O(1) - sorted in place (ignoring sort's internal cost)

class Solution2:
    def containsDuplicate(self, nums):
        nums.sort()

        for i in range(len(nums) - 1):
            if nums[i] == nums[i + 1]:
                return True
        return False


# Optimal Solution - HashSet
# Time Complexity: O(n) - single pass over the array
# Space Complexity: O(n) - set stores up to n elements

class Solution3:
    def containsDuplicate(self, nums):
        seen = set()

        for i in range(len(nums)):
            if nums[i] in seen:
                return True
            seen.add(nums[i])
        return False
