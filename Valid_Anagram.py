# https://leetcode.com/problems/valid-anagram/

# Sorting Solution
# Two anagrams become identical strings once sorted
# Time Complexity: O(n log n) - sorting dominates
# Space Complexity: O(n) - sorted copies of both strings

class Solution:
    def isAnagram(self, s, t):
        if len(s) != len(t):
            return False

        return sorted(s) == sorted(t)


# HashMap Solution
# Count characters in s, then subtract the counts of t
# Time Complexity: O(n) - one pass over each string
# Space Complexity: O(k) - k distinct characters in s

class Solution2:
    def isAnagram(self, s, t):
        if len(s) != len(t):
            return False

        # Maps character -> how many times it appears
        counts = {}

        for ch in s:
            counts[ch] = counts.get(ch, 0) + 1

        for ch in t:
            if ch not in counts:
                return False

            counts[ch] -= 1

            if counts[ch] == 0:
                del counts[ch]

        return len(counts) == 0


# Optimal Solution - Frequency Array (lowercase English letters only)
# Time Complexity: O(n) - single pass over both strings
# Space Complexity: O(1) - fixed array of 26 counters

class Solution3:
    def isAnagram(self, s, t):
        if len(s) != len(t):
            return False

        counts = [0] * 26

        for i in range(len(s)):
            counts[ord(s[i]) - ord('a')] += 1
            counts[ord(t[i]) - ord('a')] -= 1

        for count in counts:
            if count != 0:
                return False

        return True
