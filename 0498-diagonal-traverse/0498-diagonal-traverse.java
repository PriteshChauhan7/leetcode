class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];

        int m = mat.length, n = mat[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0, direction = 1; // 1 = up-right, -1 = down-left

        for (int i = 0; i < result.length; i++) {
            result[i] = mat[row][col];

            // moving up-right
            if (direction == 1) {
                if (col == n - 1) { // hit right border
                    row++;
                    direction = -1;
                } else if (row == 0) { // hit top border
                    col++;
                    direction = -1;
                } else {
                    row--;
                    col++;
                }
            } 
            // moving down-left
            else {
                if (row == m - 1) { // hit bottom border
                    col++;
                    direction = 1;
                } else if (col == 0) { // hit left border
                    row++;
                    direction = 1;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return result;
    }
}
