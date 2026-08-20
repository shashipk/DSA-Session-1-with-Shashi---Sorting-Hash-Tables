// https://leetcode.com/problems/group-anagrams/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// Brute Force
// Compare each string against one representative from every existing group
// Time Complexity: O(n^2 * k) - n strings compared against up to n groups, k = string length
// Space Complexity: O(n * k) - the output groups

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> groups = new ArrayList<>();

        for (String word : strs) {
            boolean placed = false;

            for (List<String> group : groups) {
                if (isAnagram(word, group.get(0))) {
                    group.add(word);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(word);
                groups.add(newGroup);
            }
        }

        return groups;
    }

    private boolean isAnagram(String a, String b) {

        if (a.length() != b.length()) {
            return false;
        }

        int[] counts = new int[26];

        for (int i = 0; i < a.length(); i++) {
            counts[a.charAt(i) - 'a']++;
            counts[b.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (counts[i] != 0) {
                return false;
            }
        }

        return true;
    }
}

// Sorting Solution - sorted word is the group key
// All anagrams share the same sorted string, so use it as a HashMap key
// Time Complexity: O(n * k log k) - sorting each of the n strings of length k
// Space Complexity: O(n * k) - map holds every string

class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {

        // Maps sorted word -> list of anagrams
        HashMap<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<>());
            }

            groups.get(key).add(word);
        }

        return new ArrayList<>(groups.values());
    }
}

// Optimal Solution - Character Count as the key
// Skip sorting entirely: the 26-letter frequency signature identifies the group
// Time Complexity: O(n * k) - one pass over every character
// Space Complexity: O(n * k) - map holds every string

class Solution3 {
    public List<List<String>> groupAnagrams(String[] strs) {

        // Maps count signature like "1#0#2#..." -> list of anagrams
        HashMap<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            int[] counts = new int[26];

            for (int i = 0; i < word.length(); i++) {
                counts[word.charAt(i) - 'a']++;
            }

            // Build a string key from the counts, '#' keeps digits from merging
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append(counts[i]);
                sb.append('#');
            }
            String key = sb.toString();

            if (!groups.containsKey(key)) {
                groups.put(key, new ArrayList<>());
            }

            groups.get(key).add(word);
        }

        return new ArrayList<>(groups.values());
    }
}
