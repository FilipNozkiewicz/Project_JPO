package sokoban;

import sokoban.functions.Display;
import sokoban.functions.ImageLoader;
import sokoban.functions.Spritesheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable
{
    private KeyManager keyManager;
    private Display display;
    private int width, height;
    private String title;
    private String levelpath;

    private Thread thread;

    private Player player;

    private Level level;

    private boolean isRunning; // = true;

    private int blockSize = 32;

    private BufferStrategy bs;
    private Graphics g;

    private BufferedImage testImage;
    private Spritesheet sheet;


    //Game Constructor sets up everything that is important
    public Game(String title,String levelpath){

        this.title = title;
        this.levelpath = levelpath;

        level = new Level(this.levelpath);

        width = level.getLevelXLength() * blockSize;
        height = level.getLevelYLength() * blockSize;

        player = new Player(this,level);

        keyManager = new KeyManager(player,this);

    }


    //Importing necessary assets like sprites, display
    private void init(){
        display = new Display(title,width,height);
        display.getFrame().addKeyListener(keyManager);
        testImage = ImageLoader.loadImage("/sokoban_spritesheet.png"); //loading an image using an image load method from IMAGE LOADER class
        sheet = new Spritesheet(testImage);
    }

    private void update(){
        level.setplayerCoordsX(player.getX());
        level.setplayerCoordsY(player.getY());

        level.updateMap();
        if(level.checkWin()){
            render();
            isRunning = false;
            System.out.println("Wygrałeś");
        }
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();

        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();
        //Clearing the screen
        g.clearRect(0,0,width,height);
        //Drawing here

        for(int row=0;row<level.getLevel().length;row++){
            for(int col=0;col<level.getLevel()[0].length;col++){

                if(level.getLevel()[row][col] == 0){
                    g.drawImage(sheet.crop(32,0,32,32),col*blockSize,row*blockSize,null);
                }
                else if(level.getLevel()[row][col] == 1){
                    g.drawImage(sheet.crop(32,0,32,32),col*blockSize,row*blockSize,null);
                    g.drawImage(sheet.crop(0,0,32,32),col*blockSize,row*blockSize,null);
                }
                else if(level.getLevel()[row][col] == 2){
                    g.drawImage(sheet.crop(32,32,32,32),col*blockSize,row*blockSize,null);
                }
                else if(level.getLevel()[row][col] == 3){
                    g.drawImage(sheet.crop(0,32,32,32),col*blockSize,row*blockSize,null);
                }
                else if(level.getLevel()[row][col] == 4){
                    g.drawImage(sheet.crop(64,0,32,32),col*blockSize,row*blockSize,null);
                }
                else if(level.getLevel()[row][col] == 5){
                    g.drawImage(sheet.crop(64,32,32,32),col*blockSize,row*blockSize,null);
                }

            }
        }
        //End of drawing
        bs.show();
        g.dispose();
    }

    //main loop of a game it's not perfect, but works

    public void run(){

        init();

        while(isRunning){
                System.out.println("Jestem!");
                update();
                render();
        }

    }

    public synchronized void start(){
        if(!isRunning) {
            isRunning = true;
            thread = new Thread(this);
            thread.run();
        }
    }

    public synchronized void stop(){
        if(isRunning) {
            isRunning = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //If you mess sth up, try pressing r button

    public void restart(){
        level.init();
        player.setX(level.getplayerCoordsX());
        player.setY(level.getplayerCoordsY());
        player.zeroMoveCounter();
    }
}
