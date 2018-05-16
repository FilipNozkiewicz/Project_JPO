package sokoban;

public class Chest extends Entity {

    private boolean inPosition;

    public Chest(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setInPosition(boolean x){
        inPosition = x;
    }

    public boolean getInPosition(){
        return inPosition;
    }
}
