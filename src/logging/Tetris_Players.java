package logging;

import java.util.ArrayList;

public class Tetris_Players {

    private ArrayList<Player> tetrislist = new ArrayList<> (  );

    public Tetris_Players(){
        ;
    }
    public void  add(Player player){
        tetrislist.add ( player );
    }

}
