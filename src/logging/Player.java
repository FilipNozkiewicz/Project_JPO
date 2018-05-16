package logging;

import java.util.Map;

public class Player {

    private String nick;
    private int tetrisresult =  0 ;

    public String getNick() {
        return nick;
    }
        ////////////////////GETTERS AND SETTTERS
    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getTetrisresult() {
        return tetrisresult;
    }

    public void setTetrisresult(int tetrisresult) {
        this.tetrisresult = tetrisresult;
    }

    public int getSocobanresult() {
        return socobanresult;
    }

    public void setSocobanresult(int socobanresult) {
        this.socobanresult = socobanresult;
    }

    private int socobanresult = 0 ;
    ////////////////////////////////////////////////
    public Player(String nick){
        this.nick = nick;
    }







}
