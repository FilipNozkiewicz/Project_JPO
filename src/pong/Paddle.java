package pong;

import java.awt.*;

public class Paddle {

    public int paddlenumber;

    private int speed = 5;   // this makes how fast the paddle is
    public int score = 0; // score is the element of the paddle cuse the paddle represents a player
    private int x, y;
    private final int width = 50, height = 300;  // final size of the paddle

    public Paddle(Pong pong, int paddlenumber) {
        this.paddlenumber = paddlenumber;

        if (paddlenumber == 1) {
            this.x = 0;  // set it to zero to locate the paddle at the beginning of the coordinates
        }
        if (paddlenumber == 2) {
            this.x = pong.getWidth () - width; // musze uwzglednic szerokmosc paddle gdy umieszcam je po prawej stronie

        }
        this.y = pong.getHeight () / 2 - this.height / 2;  // settting paddle's size // umiescza paddle po srodlku ale musze uwzglednic tez ich wymiar
    }

    public void render(Graphics g) {
        g.setColor ( Color.WHITE );    // setting the color for paddles to black

        // sputing paddles into JPannel

        g.fillRect ( x, y, width, height );    // tworze prostokaty majace wymiary paddli

    }

    public void startposition(Pong pong, int paddlenumber) {
        this.paddlenumber = paddlenumber;

        if (paddlenumber == 1) {
            this.x = 0;  // set it to zero to locate the paddle at the beginning of the coordinates
        }
        if (paddlenumber == 2) {
            this.x = pong.getWidth () - width;

        }
        this.y = pong.getHeight () / 2 - this.height / 2;  // settting paddle's size


    }

    public int getScore() {
        return score;
    }

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

    public void setX(int x) {
        this.x = x;
    }

    public void sumY(int x) {
        this.y += x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(boolean up) {   // true value  is a move up // and false value is a move down


        if (up) {       // umoaliwia ruch w gore
            if (y - speed > 0) {  // jeśli lokalizsacja wiosla przekracza 0 to idziemy w gore
                y -= speed;            // jesli nie to trzeba sprawic by sie znowu poijaiwlo na planszy na dole lubb gorze
            } else {                  // jesli polozenie jest nie wieksze niz gorna ramka to wykonuje ruch
                y = 0;    // conditions for not exceeding the size of the JFrame;
            }               // and coding things that gonna hapeen when itis gonna be exceeded
        } else {
            if (y + height + speed < Pong.pong.getHeight ()) {
                y += speed;  // aktualizuje polozenie przez zmiane pikseli ;
            } else {
                y = Pong.pong.getHeight () - height;  // to sprawia ze moz;liwy jest ruch w dol
            }       // jesli jesli chce przekroczyc rozmiar jframe to tworze warunek ktory utrzyma mie caly czas przy dolnej krawedzi

        }


    }
}
