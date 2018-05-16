package sokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private Player player;
    private Game game;

    public KeyManager(Player player, Game game){
        this.player = player;
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            player.Move(0,-1);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.Move(0,1);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.Move(-1,0);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.Move(1,0);
        }
        if(e.getKeyCode() == KeyEvent.VK_R){
            game.restart();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
