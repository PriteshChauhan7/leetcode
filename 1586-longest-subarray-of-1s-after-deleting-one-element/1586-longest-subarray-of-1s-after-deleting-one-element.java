class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 0, zeros = 0;
        for (int l = 0, r = 0; r < nums.length; ++r) {
            if (nums[r] == 0) ++zeros;
            while (zeros == 2) {
                if (nums[l++] == 0) --zeros;
            }
            ans = Math.max(ans, r - l);
        }
        return ans;
    }
}
