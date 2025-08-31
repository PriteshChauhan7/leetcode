class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') { // empty cell
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;

                            if (backtrack(board)) {
                                return true; // found solution
                            }

                            board[i][j] = '.'; // undo choice
                        }
                    }
                    return false; // no valid number found
                }
            }
        }
        return true; // filled entire board
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int k = 0; k < 9; k++) {
            // Check row & column
            if (board[row][k] == c || board[k][col] == c) return false;
            // Check 3x3 sub-box
            int subRow = 3 * (row / 3) + k / 3;
            int subCol = 3 * (col / 3) + k % 3;
            if (board[subRow][subCol] == c) return false;
        }
        return true;
    }
}
