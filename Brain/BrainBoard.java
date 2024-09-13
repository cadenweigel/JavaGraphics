import javax.swing.*;
import java.awt.*;

public class BrainBoard{

    private int rows;
    private int cols;
    private double initialDensity;
    private double emptyChance;
    private int[][] board;

    public BrainBoard(int rows, int cols, double initialDensity) {
        this.rows = rows;
        this.cols = cols;
        emptyChance = 1 - initialDensity;
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

        int[][] temp = new int [rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){

                int neighbors = countNeighbors(i, j);

                if (board[i][j] == 0 && neighbors == 2){
                    temp[i][j] = 1; //if off and 2 neighbors, cell turns on
                }
                else if(board[i][j] == 1){
                    temp[i][j] = 2; //if cell is on, start dying
                }
                else if(board[i][j] == 2){
                    temp[i][j] = 0; //if cell is dying, turn off
                }

            }
        }

        board = temp;

    }

    private int countNeighbors(int row, int col) { 

        //loop through neighbors above cell, then left/right, then below
        //count increases when a neighboring cells value == 1

        int neighbors = 0;

        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if (i == 0 && j == 0){
                    continue;
                } 
                else {
                    int r = row + i;
                    int c = col + j;
                    if (r >= 0 && r < rows && c >= 0 && c < cols) {
                        //verify that neighbor is within bounds of board
                        neighbors += board[r][c];
                    }
                }
            }
        }

        return neighbors;

    }

    public int[][] getBoard() {
        return board;
    }

}