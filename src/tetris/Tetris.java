package tetris;
import javax.swing.*;

public class Tetris {

    public static final int WIDTH= 330, HEIGHT = 630;
    private JFrame window = new JFrame();
    private Board board;

    private int points;

    public Tetris(){

        points = 0;

        window = new JFrame("Tetris");
        window.setSize(WIDTH,HEIGHT);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        board = new Board();

        window.add(board);
        window.addKeyListener(board);

        window.setVisible(true);
    }

    public static void main(String []args){
        new Tetris();
    }

    public int showPoints(){
        points = board.getPoints();
        return points;
    }

}
