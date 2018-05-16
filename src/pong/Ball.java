package pong;

import java.awt.*;
import java.util.Random;

public class Ball {

    private Pong pong;
    private int x , y , width = 25 , height = 25;
    public int speed = 5;

    private int amountofHits;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getAmountofHits() {
        return amountofHits;
    }

    public int getMotionX() {
        return motionX;
    }

    public int getMotionY() {
        return motionY;
    }

    private int motionX , motionY;

    private Random random;

    public Ball(Pong pong){
        this.pong = pong;
        random = new Random ( );
        spawn ();

    }
    public void update(Paddle paddle1 , Paddle padddle2) {



        this.x += motionX * speed;  // zwiekszam dwukrotnie przeskok pikseli , wizualnie przyspiesza pilke
        this.y += motionY * speed;
 ////// Sprawdzenie udereniaw sciane (gorna lub dolna krawedz ) // powoduje odbicie od sciany
       if(this.y + height > pong.getHeight () || this.y  < 0 ){
            if(this.motionY < 0){
                this.y = 0;
                this.motionY = random.nextInt ( 4 );   // generating a movement

                if(motionY == 0){
                    motionY = 1;
                }
            }
            else{
                 this.motionY = -random.nextInt ( 4 );
                 this.y = pong.getHeight() - height;

                 if(motionY == 0){
                     motionY = -1;
                 }
                   // losuje 4 wartosci zeby wprowadzic ruch oscylujay po przekatnej
            }                           // inaczej jest bardzo plaski
        }

        if(checkColission ( paddle1 ) == 1){
            this.motionX = 1 + amountofHits/5;
            this.motionY = -2 + random.nextInt ( 4 );  //

            if(motionY == 0){
                motionY = 1;
            }
            amountofHits++;
        }
        else if(checkColission (padddle2)  == 1){
            this.motionX = -1 - amountofHits/5;
            this.motionY = -2 + random.nextInt ( 4 );
            if(motionY == 0) {
                motionY = 1;
            }
            amountofHits++;
        }
        if (checkColission ( paddle1 ) == 2){
            padddle2.score++ ;      // generate a movement if there is no score;
            pong.gamestatus = 4;
            spawn ();

        }
        else if (checkColission ( padddle2 ) == 2){
            paddle1.score++;
            pong.gamestatus = 4;
            spawn ();
        }
    }
    public void spawn() {
        this.amountofHits = 0;
        this.x = pong.getWidth () /2 - this.width /2; // musze uwzglednic taklze szerokosc pilki
        this.y = pong.getHeight () / 2 - this.height / 2;
        this.motionY = -2 + random.nextInt ( 4 );
        // this.motionX = -1 + random.nextInt ( 1 );

        if(motionY == 0){
            motionY = 1;
        }
        if(random.nextBoolean ()){

            motionX = 1;
        }
        else{
            motionX = -1;
        }

    }
    public void startposition(){

    }


    public int checkColission(Paddle paddle){

        if( this.x < paddle.getX()  + paddle.getWidth () && this.x + width > paddle.getX () && this.y < paddle.getY ()
                + paddle.getHeight () && this.y + height > paddle.getY ()  ){  // when the ball hit
                return 1; //hit into the paddle
            }
            else if ((paddle.getX () > x + width && paddle.paddlenumber == 1 ) || (paddle.getX () < x - width  && paddle.paddlenumber == 2)){
               return 2 ; // hit onto the opomnets wall and score
            }

        return 0;
    }


    public void render(Graphics g ){
        g.setColor ( Color.WHITE );
        g.fillOval ( x, y , width , height );  // fill ball witch oval shape
    }
}
