import javax.swing.*;
import java.awt.*;

//https://en.wikipedia.org/wiki/Seeds_(cellular_automaton)

public class SeedsSim extends JPanel{

    private SeedsBoard game;

    public static final Color alive_color = new Color(102, 102, 255);
    public static final Color dead_color = new Color(0, 0, 0);

    public SeedsSim(SeedsBoard game) {
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
                    g.setColor(alive_color);
                } else {
                    g.setColor(dead_color);
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

        SeedsBoard game = new SeedsBoard(rows, cols, initialDensity);
        game.initializeBoard();

        JFrame frame = new JFrame("Seeds");
        SeedsSim sim = new SeedsSim(game);
        frame.add(sim);
        frame.setSize(cols, rows);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Timer(50, e -> {
            game.updateBoard();
            sim.repaint();
        }).start(); 

    }

}