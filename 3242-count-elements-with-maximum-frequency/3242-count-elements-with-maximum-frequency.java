class Solution {
    public int maxFrequencyElements(int[] nums) {
        // Because nums are positive; if there is known constraint like max value = 100, you can use array
        // Otherwise, use a HashMap
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }
        
        int maxFreq = 0;
        for (int f : freq.values()) {
            if (f > maxFreq) {
                maxFreq = f;
            }
        }
        
        int sum = 0;
        for (int f : freq.values()) {
            if (f == maxFreq) {
                sum += f;
            }
        }
        
        return sum;
    }
}
