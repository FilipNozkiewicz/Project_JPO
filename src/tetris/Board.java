package tetris;
import javax.imageio.ImageIO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class Board extends JPanel implements KeyListener {

    private int points;

    private BufferedImage blocks;

    private final int blockSize = 30;

    private final int boardWidth = 10 , boardHeight = 20;

    private int[][] board = new int[boardHeight][boardWidth];

    private Shape[] shapes = new Shape[7];

    private Shape currentShape;

    private Timer timer;

    private final int FPS = 60;

    private final int delay = 1000/FPS;

    private boolean gameOver = false;


    public Board(){
        try {
            blocks = ImageIO.read(Board.class.getResource("/tetris2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });

        timer.start();
        //shapes

        shapes[0] = new Shape (blocks.getSubimage(0,0,blockSize,blockSize),new int[][]{
                {1,1,1,1}
        },this,1);

        shapes[1] = new Shape (blocks.getSubimage(blockSize,0,blockSize,blockSize),new int[][]{
                {1,1,0},
                {0,1,1}
        },this,2);

        shapes[2] = new Shape (blocks.getSubimage(2*blockSize,0,blockSize,blockSize),new int[][]{
                {0,1,1},
                {1,1,0}
        },this,3);

        shapes[3] = new Shape (blocks.getSubimage(3*blockSize,0,blockSize,blockSize),new int[][]{
                {1,1,1},
                {0,0,1}
        },this,4);

        shapes[4] = new Shape (blocks.getSubimage(4*blockSize,0,blockSize,blockSize),new int[][]{
                {0,0,1},
                {1,1,1}
        },this,5);

        shapes[5] = new Shape (blocks.getSubimage(5*blockSize,0,blockSize,blockSize),new int[][]{
                {1,1,1},
                {0,1,0}
        },this,6);

        shapes[6] = new Shape (blocks.getSubimage(6*blockSize,0,blockSize,blockSize),new int[][]{
                {1,1},
                {1,1}
        },this,7);

        setNextShape();

        points = 0;

    }

    public void update(){

        currentShape.update();
        if(gameOver)

            timer.stop();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        currentShape.render(g);

        for (int row = 0;row<board.length;row++){
            for(int col = 0;col<board[row].length;col++){
                if(board[row][col] != 0)
                    g.drawImage(blocks.getSubimage((board[row][col]-1)*blockSize,0,
                            blockSize,blockSize),
                            col*blockSize,row*blockSize,null);
            }
        }

        for(int i=0;i<boardHeight;i++){
            g.drawLine(0,i*blockSize,boardWidth*blockSize,i*blockSize);
        }

        for(int j=0;j<boardWidth;j++){
            g.drawLine(j*blockSize,0,j*blockSize,boardHeight*blockSize);
        }

    }

    public void setNextShape(){

        int index = (int)(Math.random()*shapes.length);

        points += 1;

        Shape newShape = new Shape (shapes[index].getBlock(),shapes[index].getCoords(),
                this,shapes[index].getColor());

        currentShape = newShape;

        for(int row =0; row< currentShape.getCoords().length; row++){
            for(int col =0; col< currentShape.getCoords()[row].length;col++){
                if(currentShape.getCoords()[row][col] != 0){
                    if(board[row][col+3] != 0){
                        gameOver = true;
                    }
                }
            }
        }
    }

    public int getBlockSize(){
        return blockSize;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_LEFT){
            currentShape.setDeltaX(-1);
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            currentShape.setDeltaX(1);
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            currentShape.speedDown();
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            currentShape.Rotate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            currentShape.normalSpeed();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public int[][] getBoard(){
        return board;
    }

    public int getPoints(){
        return points;
    }

    public void givePoints(){
        points += 10;
    }
}
