import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGUI extends JFrame {
    private JTextField[][] cells = new JTextField[9][9];
    private JButton solveButton;
    private JButton resetButton;

    public SudokuGUI() {
        setTitle("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());

        // Create the grid for the Sudoku board
        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col] = new JTextField();
                cells[row][col].setHorizontalAlignment(JTextField.CENTER);
                cells[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                boardPanel.add(cells[row][col]);
            }
        }

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        resetButton = new JButton("Reset");

        buttonPanel.add(solveButton);
        buttonPanel.add(resetButton);

        add(boardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        solveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solveSudoku();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        setVisible(true);
    }

    // Method to read the board and solve the puzzle
    private void solveSudoku() {
        int[][] board = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                String text = cells[row][col].getText();
                if (!text.equals("")) {
                    board[row][col] = Integer.parseInt(text);
                } else {
                    board[row][col] = 0;
                }
            }
        }

        if (SudokuSolver.solveSudoku(board)) {
            // Update the GUI with the solved board
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    cells[row][col].setText(Integer.toString(board[row][col]));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists!");
        }
    }

    // Method to reset the Sudoku board
    private void resetBoard() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                cells[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        new SudokuGUI();
    }
}
