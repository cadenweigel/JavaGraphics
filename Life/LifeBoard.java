import javax.swing.*;
import java.awt.*;

public class LifeBoard{

    private int rows;
    private int cols;
    private double initialDensity;
    private int[][] board;

    public LifeBoard(int rows, int cols, double initialDensity) {
        this.rows = rows;
        this.cols = cols;
        this.emptyChance = 1 - initialDensity;
        board = new int[rows][cols];
    }

    public void initializeBoard() {
        // Initialize board with random values
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = Math.random() > emptyChance ? 1 : 0;
            }
        }
    }

    public void updateBoard() { 

        //creates a temp array for updating, since we don't want to modify self.board
        //until every cell has been checked

        int[][] temp = board;

    }

    private int countNeighbors(int row, int col) { 

        //loop through neighbors above cell, then left/right, then below
        //count increases when a neighboring cells value == 1

    }

    public int[][] getBoard() {
        return board;
    }

}