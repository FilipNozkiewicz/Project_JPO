package snake;


import java.awt.Point;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public  class Snake   implements ActionListener , KeyListener   {



    private boolean running ;
    private static Snake snake;
    private JFrame jframe;

    private RenderPanel renderPanel;
    private Timer timer = new Timer(20 , this::actionPerformed); //I make a Timer (Kind of thread and set a listener on this class)
    //to change an action and make the game posssible  to play by kaming sname move

    private Random random;


    private Dimension dim;
    private boolean over = false;
    private boolean paused = false;




    public  static final int UP = 0 , DOWN = 1 , LEFT = 2, RIGHT = 3; // create the final (not changable constants)
    public  static final int SCALE = 10;  // iu must set a scale for changing coordinates (*10 is a good value because i cant )
    //   make to small value , cause then i have to small change on the field;
    private int ticks = 0; // ticks are used to count a time of playing
    public int direction = DOWN; // set the default direction;
    private   int score;
    private int time;
    private int tailength = 1; // the beginning lenht of tail which will be inremented
    ///////////////   needed getters
    public static Snake getSnake() {
        return snake;
    }

    public int getTime() {
        return time;
    }

    public int getTailength() {
        return tailength;
    }

    public Point getHead() {
        return head;
    }

    public Point getCherry() {
        return cherry;
    }

    public boolean isOver() {
        return over;
    }

    ///////////////////////////////////////////////////////
    private Point head;  // create the beginning  coordintes;
    private Point cherry; // create the fruit coordinates;
    public ArrayList<Point> snakeparts = new ArrayList<Point>();  // elements of the snake body is an ArrayList of coordinates;

    public  void Snake() {

        dim = Toolkit.getDefaultToolkit ().getScreenSize ();
        jframe = new JFrame ( "Snake" );
        jframe.setVisible ( true );
        jframe.setSize ( 805, 700 );  //  Creating the basic JFrame
        jframe.setLocation ( dim.width / 2 - jframe.getWidth () / 2, dim.height / 2 - jframe.getHeight () / 2 ); // just location of frame
        jframe.add ( renderPanel = new RenderPanel () );
        // jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable ( false );
        jframe.addKeyListener ( this );
        startGame ();


    }

    public void startGame(){

        over = false;
        paused = false;
        score =0;
        ticks = 0;
        time = 0;
        tailength = 1;


        direction = DOWN;
        head = new Point(0,-1);
        random = new Random();
        snakeparts.clear();
        cherry = new Point(random.nextInt(20),random.nextInt(20));

        for (int i = 0 ; i <tailength ; i++){
            snakeparts.add(new Point(head.x , head.y));
        }
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {



      //  sound.start ();
        renderPanel.repaint();  // i got to repaint the jframe all the time to make a move
        ticks++;
        if (ticks % 2 == 0 && head != null && !over && !paused){ // check if the cherry is not on the head because then the game is over;

            time++;
            snakeparts.add(new Point (head.x , head.y)); // ad a new coordonate to an ArrayList of snakepody(coordinates)
            if(direction == UP){                           // later i got to remove the first one coordiunate from an arraylist;
                if(head.y - 1 >= 0 && noTailAt(head.x , head.y - 1)){
                    head = new Point(head.x , head.y - 1);  //  check if my snake is inside my field 4 times for 4 directions
                }
                else {
                    over = true;

                }
            }
            if(direction == DOWN){
                if(head.y + 1 < 67 && noTailAt(head.x , head.y + 1)){ // check if i am inside the field and if i make a move in that direction the head wont be on a tail
                    head = new Point(head.x , head.y + 1);
                }
                else {
                    over = true;   // in the other case the game is over

                }
            }
            if(direction == LEFT && noTailAt(head.x - 1 , head.y )){
                if(head.x - 1 >= 0){
                    head = new  Point(head.x-1 , head.y );
                }
                else {
                    over = true;

                }

            }
            if(direction == RIGHT && noTailAt(head.x + 1 , head.y )){
                if(head.x + 1 < 80){
                    head = new  Point(head.x +1, head.y);
                }
                else {
                    over = true;   // instead the fruit

                }

            }

           if (snakeparts.size() > tailength)
                snakeparts.remove(0);
            if (cherry != null){
              //  System.out.println("sdds");
                if(head.equals(cherry)){ // if (head.x == cherry.x && head.y == cherry.y){
                    score+=10;             // increment a score;
                    tailength++;         // increments the length of the snake
                    cherry.setLocation(random.nextInt(77) , random.nextInt(65)); // random location of the fruit
                    System.out.println (score);
                    //if the game is not over for each cndtition i must remove
                }                                                   // the begging (the first element of an array which contains body)
            }


        }
        if(over == true){
           // System.out.println (score);
           // System.out.println (score);
        }


        }

    public boolean noTailAt(int x, int y) {
        for(Point  point : snakeparts){
            if (point.equals(new Point(x,y))) {  // there is cheded whether (cialo weza sie nie styka)
                //  if any of the points equal with new point it does mean that body of the snake is eaten by himself
                // over = true;
                return false;

            }
        }
        return true;
    }

    public   int getScore() {
        return score;
    }

    public  void dosth(){
        snake = new Snake (); // the method which will be called in the gui to run a game
        snake.Snake ();


    }

    public static void main(String[] args){
      // dosth ();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();

        if (i == KeyEvent.VK_A  && direction != RIGHT) {
            direction = LEFT;
        }

        if (i == KeyEvent.VK_D  && direction != LEFT) {
            direction = RIGHT;
        }

        if (i == KeyEvent.VK_W  && direction != DOWN) {
            direction = UP;
        }

        if (i == KeyEvent.VK_S  && direction != UP) {
            direction = DOWN;
        }
        if (i == KeyEvent.VK_SPACE){
            if(over) startGame();
            else {
                paused = !paused;

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {      // i must implements method if my class implements an interface KeyPressed

    }



}