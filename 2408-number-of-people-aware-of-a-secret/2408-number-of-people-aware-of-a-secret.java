class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        final int MOD = 1_000_000_007;

        int[] dp = new int[n];
        dp[0] = 1; // Day 1: one person learns the secret

        long share = 0; // People currently able to share

        for (int day = 1; day < n; day++) {
            // Start sharing: those who learned on day - delay
            if (day - delay >= 0) {
                share = (share + dp[day - delay]) % MOD;
            }
            // Stop sharing: those who learned on day - forget
            if (day - forget >= 0) {
                share = (share - dp[day - forget] + MOD) % MOD;
            }
            dp[day] = (int) share;
        }

        // Count people who still remember the secret on day n
        long result = 0;
        for (int i = n - forget; i < n; i++) {
            if (i >= 0) result = (result + dp[i]) % MOD;
        }

        return (int) result;
    }
}
