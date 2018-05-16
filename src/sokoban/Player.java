package sokoban;

public class Player {

    private int x,y;

    private Game game;
    private Level level;
    private int moveCounter;

    public Player(Game game,Level level){

        this.game = game;
        this.level = level;
        this.x = level.getplayerCoordsX();
        this.y = level.getplayerCoordsY();
    }


    public void Move(int deltaX,int deltaY) {

        if (!level.neighbourCollision(x, y,deltaX,deltaY)) {

                if (x + deltaX >= 0 && x + deltaX <= level.getLevelXLength() - 1) {

                    x += deltaX;
                }

                if (y + deltaY >= 0 && y + deltaY <= level.getLevelYLength() - 1) {

                    y += deltaY;

                }

                moveCounter++;
                System.out.println(moveCounter);
        }

    }

    public int getX(){
        return x;
    }

    public void setX(int x) {this.x = x;}

    public int getY(){
        return y;
    }

    public void setY(int y) {this.y = y;}

    public void zeroMoveCounter(){
        moveCounter = 0;
    }

    public int getMoveCounter(){
        return moveCounter;
    }

}
