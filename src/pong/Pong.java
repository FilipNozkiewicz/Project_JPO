package pong;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Pong implements ActionListener , KeyListener {

    public static Pong pong;
    private String Winner;

    private final int scoreLimit = 3;
    private Random random;
    private boolean issingleplayer;
    public int gamestatus = 0; // 1 = Playing , 0 = Stopped , 2 = Pause , 3 = finished

    Paddle paddle;

    private Ball ball;

    private int botDifficulty ;
    private boolean selectingDifficulty;

    private int width = 700, height = 700;
     Renderer renderer;

     private Paddle  player1 , player2;
     private int botMoves;
     private  int botCoolDown = 0;

     private boolean bot ;

     private boolean w = false, s = false ,up = false , down = false;  // variables for keys

    public static Pong getPong() {
        return pong;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void Pong(){

        random = new Random (  );
        Timer timer  = new Timer(20 , this); // twoze watek na Timerze
        JFrame jframe = new JFrame ( "Pong" );   // Tworzenie podstawowego jframe
        renderer = new Renderer();

        jframe.setSize ( width + 17   , height + 30);
     //   jframe.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        jframe.setVisible ( true );
        jframe.add ( renderer );
        jframe.addKeyListener ( this );
        start ();

        timer.start ();
    }

    public void start(){
        selectingDifficulty = true;
        botDifficulty = 0;
        gamestatus = 0;
        player1 = new Paddle ( this , 1 );
        player2 = new Paddle (  this ,2 );
        player1.score = 0 ;
        player2.score = 0;
        ball = new Ball(this);


    }


    public void update() {
        //System.out.println (Renderer.serialVersionUID);  // method for updating location of the paddles depends on pressed key;
        if (w) {
            player1.move ( true );  // if w = false to ruszam w dol
        }
        if (s) {
            player1.move ( false );    // true is for moving up
        }// false is for  moving down;
        if (!bot) {
            ball.speed = 3;
            if (up) {
                player2.move ( true );
            }
            if (down) {
                player2.move ( false );
            }
            ball.update ( player1, player2 );
        } else { // generating move based on paddle location and a ball location
            //   int speed = 15;
            if(botCoolDown > 0){
                botCoolDown--;

                if(botCoolDown == 0){
                    botMoves = 0;
                }
            }
            if (botMoves < 10) {
                if (player2.getY () + player2.getHeight () / 2 < ball.getY ()) {
                    player2.move ( false );
                    //player2.sumY ( speed );
                    botMoves--;
                }
                if (player2.getY () + player2.getHeight () / 2 > ball.getY ()) {
                    player2.move ( true );
                    // player2.sumY ( -speed );
                    botMoves++;  // settings for playing with a bot
                }
                if (random.nextInt ( 50 ) < 10) {
                    player2.move ( true );
                }
                if (random.nextInt ( 50 ) > 40) {
                    player2.move ( false );
               }

            if(botDifficulty == 0){
                botCoolDown = 10;
            }
            if(botDifficulty == 1){    // setting the speed to the difficulty;
                botCoolDown = 20;
            }
            if(botDifficulty == 2){
                botCoolDown = 30;
            }
            }
        }

        ball.update ( player1 , player2 ); // visual update
    }
    public void render(Graphics2D g){

        g.setColor ( Color.BLACK );
        g.fillRect ( 0 , 0  ,width , height );
        g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON); // Quality of rendering

        // it makes the quality of the string much better ;

        if (gamestatus == 0){

            g.setColor ( Color.WHITE );
            g.setFont ( new Font("Arial" , 1 , 30) );
            g.drawString ( "Pong" , width/2  - 40 , 25 );
            if (!selectingDifficulty) {
                g.drawString ( "Press space to play multiplayer", width / 2 - 230, 70 );
                g.drawString ( "Press shift to play singleplayer", width / 2 - 230, 110 );
            }
        }
        if(selectingDifficulty){
            String string = botDifficulty == 0 ? "Easy" : botDifficulty == 1 ? "Medium " : "Hard";
            g.setFont(new Font("Arial" , 1 , 30));
            g.drawString ( "Select Difficculty: " + string ,width / 2  - 150, height / 2 - 25  );
            g.drawString ( "Press space to play multiplayer" ,width / 2  - 150, height / 2 + 10  );
            g.drawString ( "Press shift to play singleplayer" ,width / 2  - 150, height / 2 + 50  );
        }
        if (gamestatus == 2 || gamestatus == 1 ) {

            g.setColor ( Color.WHITE );
            g.setStroke ( new BasicStroke ( 4 ) );
            g.drawLine ( width / 2, 0, width / 2, height );
            g.setStroke (new BasicStroke ( 5 )); // function of decoration // this time i set the width of the line of the circle
            g.drawOval ( width / 2 - 100 , height / 2 - 100 , 200 , 200  );// line of the field withc width of
            // 1st x coordinate (1 end of a line) , 2nd x coordinate (second end of the l;ine of x )
            // (1st end of line on y coord , and the 2nd end of line on y coordinate)
            g.setFont ( new Font("Arial" , 1 , 50) ); // draw the circle int the middle of the field
            g.drawString ( String.valueOf ( player1.score ) , width/2 - 45 , 80 );
            g.drawString ( String.valueOf ( player2.score ) , width/2 + 15 , 80 );
            player1.render ( g );
            player2.render ( g );
            ball.render ( g );
        }
        if (gamestatus == 2){
            g.setColor ( Color.WHITE );
            g.setFont ( new Font("Arial" , 1 , 50) );
            g.drawString ( "Pause" , width / 2 - 70 , 40 );
        }
        if (gamestatus == 4){
            g.setColor ( Color.WHITE );
            g.setFont ( new Font("Arial" , 1 , 50) );
         //   g.drawString ( "Pause" , width / 2 - 70 , 40 );
            g.setColor ( Color.WHITE );
            g.setStroke ( new BasicStroke ( 4 ) );
            g.drawLine ( width / 2, 0, width / 2, height );
            g.setStroke (new BasicStroke ( 5 )); // function of decoration // this time i set the width of the line of the circle
            g.drawOval ( width / 2 - 100 , height / 2 - 100 , 200 , 200  );// line of the field withc width of
            // 1st x coordinate (1 end of a line) , 2nd x coordinate (second end of the l;ine of x )
            // (1st end of line on y coord , and the 2nd end of line on y coordinate)
            g.setFont ( new Font("Arial" , 1 , 50) ); // draw the circle int the middle of the field
            g.drawString ( String.valueOf ( player1.score ) , width/2 - 45 , 80 );
            g.drawString ( String.valueOf ( player2.score ) , width/2 + 15 , 80 );
            player1.startposition (  this , 1);
            player2.startposition (  this , 2);
            player1.render ( g );
            player2.render ( g );
            ball.render ( g );
        }
        if (gamestatus == 3){
            g.setColor ( Color.WHITE );
            g.setFont ( new Font("Arial" , 1 , 20) );
            g.drawString ( "Finished" , width / 2 - 70 , 40 );
            g.drawString (  Winner + " Won" , width / 2 - 100 , 80 );
        }


    }
    public  void doSth(){
        pong = new Pong ();
        pong.Pong ();
    }
    public  static  void main(String []args){

       // doSth ();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(gamestatus == 1) {
            update ();
        }
        if(player1.score == scoreLimit){
            Winner = "Player1";
            gamestatus = 3;

        }
        if(player2.score == scoreLimit){
            Winner = "Player2";
            gamestatus = 3;

        }
        renderer.repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public int getScoreLimit() {
        return scoreLimit;
    }

    public int getGamestatus() {
        return gamestatus;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getKeyCode () ;

        if(id == KeyEvent.VK_W)
        {
            w = true;
        }
        if(id == KeyEvent.VK_S){
            s = true;
        }
        if(id == KeyEvent.VK_UP)
        {
            up = true;
        }
        if(id == KeyEvent.VK_DOWN){
            down = true;

        }
        if(id == KeyEvent.VK_ESCAPE ){

            start ();
        }
        if (id == KeyEvent.VK_SHIFT && gamestatus == 0){
            if(gamestatus == 0){
                start ();
                bot = true;
                gamestatus = 2;
                issingleplayer = true;

            }
        }
        if(id == KeyEvent.VK_RIGHT ){
            botDifficulty ++;
            if(botDifficulty > 2)
                botDifficulty = 0;
        }
        if (id == KeyEvent.VK_SPACE){
            if(gamestatus == 0 ){
                start ();
                bot = false;
                issingleplayer = false;
                gamestatus = 2;
                selectingDifficulty = false;
            }
            else if(gamestatus == 2 || gamestatus == 4){
                gamestatus = 1;
            }

            else if(gamestatus == 1){
                gamestatus = 2;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int id = e.getKeyCode () ;

        if(id == KeyEvent.VK_W)
        {
            w = false;
        }
        if(id == KeyEvent.VK_S){
            s = false;
        }
        if(id == KeyEvent.VK_UP)   // for multiplayer
        {
            up = false;
        }
        if(id == KeyEvent.VK_DOWN){
            down = false;
        }

    }
}
