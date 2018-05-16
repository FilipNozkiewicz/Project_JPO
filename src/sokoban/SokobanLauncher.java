package sokoban;

public class SokobanLauncher{

    public void run(){
        Game game = new Game("Title!","resources/Level3.txt");
        game.start();
    }
}
