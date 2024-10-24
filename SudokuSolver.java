public class SudokuSolver {
    // Method to check if placing a number is valid
    public static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if the number is in the row or column
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is in the 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Backtracking method to solve the Sudoku
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // If the cell is empty
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            // Recurse to solve the rest of the board
                            if (solveSudoku(board)) {
                                return true;
                            }
                            // If placing num doesn't lead to a solution, reset the cell
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true; // If all cells are filled correctly
    }
}
