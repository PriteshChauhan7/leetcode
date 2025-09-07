class Solution {
    public int[] sumZero(int n) {
        int[] res = new int[n];
        int index = 0;

        // Fill with pairs (i, -i)
        for (int i = 1; i <= n / 2; i++) {
            res[index++] = i;
            res[index++] = -i;
        }

        // If n is odd, include 0
        if (n % 2 == 1) {
            res[index] = 0;
        }

        return res;
    }
}
