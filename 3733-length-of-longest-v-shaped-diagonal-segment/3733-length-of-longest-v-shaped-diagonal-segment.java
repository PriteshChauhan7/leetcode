class Solution {
    private static final int[][] dirs = {{-1,1}, {1,1}, {1,-1}, {-1,-1}};

    public int lenOfVDiagonal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Integer[][][][][] mem = new Integer[m][n][2][2][4];
        int ans = 0;
        
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1)
                    for (int d = 0; d < 4; d++) {
                        int dx = dirs[d][0], dy = dirs[d][1];
                        ans = Math.max(ans, 1 + dfs(grid, i + dx, j + dy, false, 2, d, mem));
                    }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, boolean turned, int num, int dir, Integer[][][][][] mem) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != num)
            return 0;

        int hashNum = Math.max(0, num - 1);
        int t = turned ? 1 : 0;
        if (mem[i][j][t][hashNum][dir] != null)
            return mem[i][j][t][hashNum][dir];

        int nextNum = (num == 2 ? 0 : 2);
        int dx = dirs[dir][0], dy = dirs[dir][1];
        int res = 1 + dfs(grid, i + dx, j + dy, turned, nextNum, dir, mem);

        if (!turned) {
            int nextDir = (dir + 1) % 4;
            int ndx = dirs[nextDir][0], ndy = dirs[nextDir][1];
            res = Math.max(res, 1 + dfs(grid, i + ndx, j + ndy, true, nextNum, nextDir, mem));
        }

        return mem[i][j][t][hashNum][dir] = res;
    }
}
