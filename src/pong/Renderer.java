package pong;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {

    public static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent ( g );   // wywoluje kostruktor klasy nadrzednej czyli JPannel;

        Pong.pong.render ( (Graphics2D)g );
    }
}
