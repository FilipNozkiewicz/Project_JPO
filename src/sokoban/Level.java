package sokoban;
import sokoban.functions.LevelLoader;

import java.util.ArrayList;

public class Level {

    private int playerX_previous;
    private int playerY_previous;

    private int playerX_new;
    private int playerY_new;

    //Loading levels variables

    private String path;
    private int width;
    private int height;

    private boolean a[];

    //Entities

    private Chest chest;
    private ArrayList<Chest> chests;

    private Goal goal;
    private ArrayList<Goal> goals;

    private LevelLoader levelLoader = new LevelLoader();

    private int[][] level;

    public Level(String path){
        this.path = path;
        init();
    }

    public int[][] getLevel(){
        return level;
    }

    public int getLevelYLength(){
        return level.length;
    }

    public int getLevelXLength() {return level[0].length;}

    public void setplayerCoordsX(int px){
        playerX_new = px;
    }

    public void setplayerCoordsY(int py){
        playerY_new = py;
    }

    public int getplayerCoordsX(){
        return playerX_previous;
    }

    public int getplayerCoordsY(){
        return playerY_previous;
    }

    public void init(){

        chests = new ArrayList<Chest>();
        goals = new ArrayList<Goal>();

        String file = levelLoader.loadFileAsString(path);
        String [] tokens = file.split("\\s+");
        width = levelLoader.parseInt(tokens[0]);
        height = levelLoader.parseInt(tokens[1]);

        level = new int[width][height];

        for(int row=0;row<width;row++){
            for(int col=0;col<height;col++){
                level[row][col] = levelLoader.parseInt(tokens[(row + col*width)+2]);
            }
        }

        for(int row=0;row<level.length;row++){
            for(int col=0;col<level[0].length;col++){
                if(level[row][col] == 1) {
                    playerX_previous = col;
                    playerY_previous = row;
                }
                if(level[row][col] == 2){
                    chest = new Chest(col,row);
                    chests.add(chest);
                }
                if(level[row][col] == 5){
                    chest = new Chest(col,row);
                    chest.setInPosition(true);
                    chests.add(chest);
                }
                if(level[row][col] == 4 || level[row][col] == 5){
                    goal = new Goal(col,row);
                    goals.add(goal);
                }
            }
        }
        a = new boolean[chests.size()];
    }


    public void updateMap(){

        //Setting goals

        for(int i=0;i<chests.size();i++){
            level[chests.get(i).getY()][chests.get(i).getX()] = 2;
        }

        for(int i=0;i<goals.size();i++){


            if(playerX_new == goals.get(i).getX() && playerY_new == goals.get(i).getY()){
                level[goals.get(i).getY()][goals.get(i).getX()] = 1;
            } else {
                level[goals.get(i).getY()][goals.get(i).getX()] = 4;
            }

            for(int j=0;j<chests.size();j++){
                if(chests.get(j).getX()==goals.get(i).getX() && chests.get(j).getY() == goals.get(i).getY()){
                    level[chests.get(j).getY()][chests.get(j).getX()] = 5;
                }
            }

        }

        level[playerY_previous][playerX_previous] = 0;
        level[playerY_new][playerX_new] = 1;

        playerX_previous = playerX_new;
        playerY_previous = playerY_new;

    }

    public boolean neighbourCollision(int object_x,int object_y,int movement_x,int movement_y){

        boolean flag = false;

        //Checking Player - Wall collison
        if(level[object_y+movement_y][object_x+movement_x]==3 ||
                level[object_y+movement_y][object_x+movement_x]==5) {
            flag = true;
        }

        //Then go to Player - Chest collision

        for(int i=0;i<chests.size();i++) {

            if (object_x + movement_x == chests.get(i).getX() && object_y + movement_y == chests.get(i).getY()) {

                if (level[object_y + movement_y][object_x + movement_x] == 2 ||
                        level[object_y + movement_y][object_x + movement_x] == 5) {

                    // Checking if Chest move avaliable
                    if (level[object_y + movement_y + movement_y][object_x + movement_x + movement_x] == 0) {

                            chests.get(i).setX(object_x + movement_x + movement_x);
                            chests.get(i).setY(object_y + movement_y + movement_y);

                        chests.get(i).setInPosition(false);
                        flag = false;

                        // Checking Chest - Chest  Chest - Wall collision
                    } else if (level[object_y + movement_y + movement_y][object_x + movement_x + movement_x] == 2
                            || level[object_y + movement_y + movement_y][object_x + movement_x + movement_x] == 5
                            || level[object_y + movement_y + movement_y][object_x + movement_x + movement_x] == 3) {

                        //chests.get(i).setInPosition(false);
                        flag = true;

                        // Checking Chest - Goal_Field collision
                    } else if (level[object_y + movement_y + movement_y][object_x + movement_x + movement_x] == 4) {
                        // Changing a Chest position

                            chests.get(i).setX(object_x + movement_x + movement_x);
                            chests.get(i).setY(object_y + movement_y + movement_y);
                            chests.get(i).setInPosition(true);

                        flag = false;
                    }

                }

            }

        }
        return flag;
    }

    public boolean checkWin(){

            //Checking if all chests are in position

            for(int j=0;j<chests.size();j++){

                if(chests.get(j).getInPosition()) {
                    a[j] = true;
                } else a[j] = false;

            }

            if(areAlltrue(a)){
                    return true;
            }

            return false;

    }

    public boolean areAlltrue(boolean[] array){
        for(boolean b : array){
            if(!b) return false;
        }
        return true;
    }

}
