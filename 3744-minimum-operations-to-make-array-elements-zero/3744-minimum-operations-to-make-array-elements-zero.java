class Solution {
  public long minOperations(int[][] queries) {
    long ans = 0;
    for (int[] query : queries) {
      int l = query[0];
      int r = query[1];
      ans += (getOperations(r) - getOperations(l - 1) + 1) / 2;
    }
    return ans;
  }

  // Computes total operations needed for numbers from 1 to n
  private long getOperations(int n) {
    long res = 0;
    int ops = 0;
    for (long powerOfFour = 1; powerOfFour <= n; powerOfFour *= 4) {
      int l = (int) powerOfFour;
      int r = (int) Math.min(n, powerOfFour * 4 - 1);
      ops += 1;
      res += (long) (r - l + 1) * ops;
    }
    return res;
  }
}
