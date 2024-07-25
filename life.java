import java.awt.*;
import java.awt.Graphics2D;

import javax.swing.JFrame; 
@SuppressWarnings("unused")

//https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life sim rules
//https://en.wikipedia.org/wiki/Brian%27s_Brain for later!

public class life{

    public static void main(String args[]){

        System.out.println("Hello!");
        createFrame(500, 500);

    }

    private static void createFrame(int width, int height){

        JFrame frame = new JFrame("Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);

    }

}

