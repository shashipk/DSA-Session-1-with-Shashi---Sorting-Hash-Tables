// https://leetcode.com/problems/valid-anagram/

import java.util.Arrays;
import java.util.HashMap;

// Sorting Solution
// Two anagrams become identical strings once sorted
// Time Complexity: O(n log n) - sorting dominates
// Space Complexity: O(n) - char arrays for both strings

class Solution {
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        Arrays.sort(sChars);
        Arrays.sort(tChars);

        return Arrays.equals(sChars, tChars);
    }
}

// HashMap Solution
// Count characters in s, then subtract the counts of t
// Time Complexity: O(n) - one pass over each string
// Space Complexity: O(k) - k distinct characters in s

class Solution2 {
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        // Maps character -> how many times it appears
        HashMap<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);

            if (!counts.containsKey(ch)) {
                return false;
            }

            counts.put(ch, counts.get(ch) - 1);

            if (counts.get(ch) == 0) {
                counts.remove(ch);
            }
        }

        return counts.isEmpty();
    }
}

// Optimal Solution - Frequency Array (lowercase English letters only)
// Time Complexity: O(n) - single pass over both strings
// Space Complexity: O(1) - fixed array of 26 counters

class Solution3 {
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }

        return true;
    }
}
