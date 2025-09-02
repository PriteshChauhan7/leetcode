class Solution {
    public int numberOfPairs(int[][] points) {
        Arrays.sort(points, (a, b) -> a[0] == b[0]
                                      ? b[1] - a[1]
                                      : a[0] - b[0]);
        int cnt = 0;
        for (int i = 0; i < points.length; i++) {
            int maxY = Integer.MIN_VALUE;
            int y_i = points[i][1];
            for (int j = i + 1; j < points.length; j++) {
                int y_j = points[j][1];
                if (y_j <= y_i && y_j > maxY) {
                    cnt++;
                    maxY = y_j;
                }
            }
        }
        return cnt;
    }
}
