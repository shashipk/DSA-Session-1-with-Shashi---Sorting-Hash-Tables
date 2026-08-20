# https://leetcode.com/problems/group-anagrams/

# Brute Force
# Compare each string against one representative from every existing group
# Time Complexity: O(n^2 * k) - n strings compared against up to n groups, k = string length
# Space Complexity: O(n * k) - the output groups

class Solution:
    def groupAnagrams(self, strs):
        groups = []

        for word in strs:
            placed = False

            for group in groups:
                if self.is_anagram(word, group[0]):
                    group.append(word)
                    placed = True
                    break

            if not placed:
                groups.append([word])

        return groups

    def is_anagram(self, a, b):
        if len(a) != len(b):
            return False

        counts = [0] * 26

        for i in range(len(a)):
            counts[ord(a[i]) - ord('a')] += 1
            counts[ord(b[i]) - ord('a')] -= 1

        for count in counts:
            if count != 0:
                return False

        return True


# Sorting Solution - sorted word is the group key
# All anagrams share the same sorted string, so use it as a HashMap key
# Time Complexity: O(n * k log k) - sorting each of the n strings of length k
# Space Complexity: O(n * k) - map holds every string

class Solution2:
    def groupAnagrams(self, strs):
        # Maps sorted word -> list of anagrams
        groups = {}

        for word in strs:
            key = "".join(sorted(word))

            if key not in groups:
                groups[key] = []

            groups[key].append(word)

        return list(groups.values())


# Optimal Solution - Character Count as the key
# Skip sorting entirely: the 26-letter frequency tuple identifies the group
# Time Complexity: O(n * k) - one pass over every character
# Space Complexity: O(n * k) - map holds every string

class Solution3:
    def groupAnagrams(self, strs):
        # Maps 26-length count tuple -> list of anagrams
        groups = {}

        for word in strs:
            counts = [0] * 26

            for ch in word:
                counts[ord(ch) - ord('a')] += 1

            # Lists are not hashable, so use a tuple as the key
            key = tuple(counts)

            if key not in groups:
                groups[key] = []

            groups[key].append(word)

        return list(groups.values())
