import javax.swing.*;
import java.awt.*;

//https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life sim rules
//https://en.wikipedia.org/wiki/Brian%27s_Brain for later!

public class LifeSim extends JPanel{

    private LifeBoard game;

    public LifeSim(LifeBoard game) {
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] board = game.getBoard();
        int cellSize = 5;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                //g.setColor(Color.GRAY);
                //g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }

    public static void main(String args[]){

        int rows = 500;
        int cols = 500;
        double initialDensity = 0.25;

        LifeBoard game = new LifeBoard(rows, cols, initialDensity);
        game.initializeBoard();

        JFrame frame = new JFrame("Conway's Game of Life");
        LifeSim sim = new LifeSim(game);
        frame.add(sim);
        frame.setSize(cols, rows);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        /* new Timer(50, e -> {
            game.updateBoard();
            sim.repaint();
        }).start(); */

    }

}