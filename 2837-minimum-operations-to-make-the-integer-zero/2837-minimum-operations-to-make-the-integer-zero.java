class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        for (long k = 0; k <= 60; ++k) {
            long target = num1 - k * (long)num2;
            if (target >= k && Long.bitCount(target) <= k) {
                return (int) k;
            }
        }
        return -1;
    }
}
