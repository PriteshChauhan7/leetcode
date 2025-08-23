class Solution {
    // prefix sum of 1s: ruc[r][c] = # of ones in rectangle [0..r) x [0..c)
    private int[][] ruc;
    private int H, W;

    // ones in [r0..r1) x [c0..c1)
    private int ones(int r0, int c0, int r1, int c1) {
        return ruc[r1][c1] - ruc[r0][c1] - ruc[r1][c0] + ruc[r0][c0];
    }

    // minimal area of a rectangle that covers all 1s inside the region;
    // returns 0 if there are no 1s in the region
    private int tightArea(int r0, int c0, int r1, int c1) {
        if (ones(r0, c0, r1, c1) == 0) return 0;

        int top = r0, bot = r1 - 1, left = c0, right = c1 - 1;
        while (ones(top, c0, top + 1, c1) == 0) top++;
        while (ones(bot, c0, bot + 1, c1) == 0) bot--;
        while (ones(r0, left, r1, left + 1) == 0) left++;
        while (ones(r0, right, r1, right + 1) == 0) right--;

        return (bot - top + 1) * (right - left + 1);
    }

    // split a region into TWO parts with a straight cut, and sum the tight areas if both parts contain 1s
    private int bestTwo(int r0, int c0, int r1, int c1, boolean verticalCut) {
        int ans = Integer.MAX_VALUE;
        if (verticalCut) {
            for (int c = c0 + 1; c < c1; c++) {
                int a1 = tightArea(r0, c0, r1, c);
                if (a1 == 0) continue; // left has no 1s
                int a2 = tightArea(r0, c, r1, c1);
                if (a2 != 0) ans = Math.min(ans, a1 + a2);
            }
        } else {
            for (int r = r0 + 1; r < r1; r++) {
                int a1 = tightArea(r0, c0, r, c1);
                if (a1 == 0) continue; // top has no 1s
                int a2 = tightArea(r, c0, r1, c1);
                if (a2 != 0) ans = Math.min(ans, a1 + a2);
            }
        }
        return ans;
    }

    // make two cuts: firstCut vertical/horizontal; choose which side is the single-rectangle side (takeLowerSide);
    // secondCut orientation for the other side.
    private int bestThree(boolean firstCutVertical, boolean singleOnLowerSide, boolean secondCutVertical) {
        int ans = Integer.MAX_VALUE;
        if (firstCutVertical) {
            for (int c = 1; c < W; c++) {
                int aSingle, aDouble;
                if (singleOnLowerSide) { // single rectangle on the left [0..c)
                    aSingle = tightArea(0, 0, H, c);
                    if (aSingle == 0) continue;
                    aDouble = bestTwo(0, c, H, W, secondCutVertical);
                } else { // single rectangle on the right [c..W)
                    aSingle = tightArea(0, c, H, W);
                    if (aSingle == 0) continue;
                    aDouble = bestTwo(0, 0, H, c, secondCutVertical);
                }
                if (aDouble != Integer.MAX_VALUE) ans = Math.min(ans, aSingle + aDouble);
            }
        } else { // first cut horizontal
            for (int r = 1; r < H; r++) {
                int aSingle, aDouble;
                if (singleOnLowerSide) { // single rectangle on the top [0..r)
                    aSingle = tightArea(0, 0, r, W);
                    if (aSingle == 0) continue;
                    aDouble = bestTwo(r, 0, H, W, secondCutVertical);
                } else { // single rectangle on the bottom [r..H)
                    aSingle = tightArea(r, 0, H, W);
                    if (aSingle == 0) continue;
                    aDouble = bestTwo(0, 0, r, W, secondCutVertical);
                }
                if (aDouble != Integer.MAX_VALUE) ans = Math.min(ans, aSingle + aDouble);
            }
        }
        return ans;
    }

    public int minimumSum(int[][] grid) {
        H = grid.length;
        W = grid[0].length;

        ruc = new int[H + 1][W + 1];
        for (int i = 0; i < H; i++) {
            int rowSum = 0;
            for (int j = 0; j < W; j++) {
                rowSum += grid[i][j];
                ruc[i + 1][j + 1] = ruc[i][j + 1] + rowSum;
            }
        }

        int ans = Integer.MAX_VALUE;
        ans = Math.min(ans, bestThree(true,  true,  true));
        ans = Math.min(ans, bestThree(true,  true,  false));
        ans = Math.min(ans, bestThree(true,  false, false));
        ans = Math.min(ans, bestThree(false, true,  true));
        ans = Math.min(ans, bestThree(false, true,  false));
        ans = Math.min(ans, bestThree(false, false, true));
        return ans;
    }
}
