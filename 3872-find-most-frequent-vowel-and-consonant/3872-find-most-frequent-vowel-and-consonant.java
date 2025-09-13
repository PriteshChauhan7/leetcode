class Solution {
    public int maxFreqSum(String s) {
        int[] count = new int[26];   // counts for 'a' to 'z'
        
        // Step 1: Count frequency of each letter
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        int maxVowel = 0;
        int maxConsonant = 0;

        // Step 2: Find max frequency among vowels and consonants
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');  // get the actual character
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                maxVowel = Math.max(maxVowel, count[i]);
            } else {
                maxConsonant = Math.max(maxConsonant, count[i]);
            }
        }

        // Step 3: Return sum of the two
        return maxVowel + maxConsonant;
    }
}
