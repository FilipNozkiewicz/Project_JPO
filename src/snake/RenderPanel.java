


package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {
    public  static Color green = new Color(1666073);
    //  public static int curColor = 0;
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(green);
        g.fillRect(0, 0, 805, 700);  // the amount of surface of my panel i want to fulfil with that color;
         Snake snake = Snake.getSnake ();
        //Snake snake = new Snake ();
        g.setColor(Color.BLUE);
        for (Point point : snake.snakeparts) {
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE); // fill panel with al the parts of snake
            /// int the proper place of the panel;
        }
        g.fillRect(snake.getHead ().x * Snake.SCALE , snake.getHead ().y * Snake.SCALE , Snake.SCALE ,  Snake.SCALE );
        g.setColor(Color.RED);
        g.fillRect(snake.getCherry ().x * Snake.SCALE, snake.getCherry ().y * Snake.SCALE, Snake.SCALE, Snake.SCALE);// set the head of the snake
        // inte the proper place of the panel ;
        String string = "Score: " + snake.getScore () + ", Length: " + snake.getTailength () + " , Time: " + snake.getTime () / 20;
        g.setColor ( Color.white );

          // drawing points and time in a proper place of the Jframe
        if(snake.isOver () == true) {
            g.drawString ( "GAMEOVER", (int) (getWidth () / 2 - string.length () * 1.5f), 10 );
            g.drawString ( "Your Score " + snake.getScore (), (int) (getWidth () / 2 - string.length () * 1.5f), 30 );
          //  System.out.println (snake.score);
        } else
            g.drawString ( string , (int)(getWidth () / 2 - string.length() * 1.5f) , 10);

    }
}