import audio.Sound;
import logging.Player;
import pong.Pong;
import snake.Snake;
import sokoban.*;
import tetris.Tetris;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Platform {

    private JPanel main_panel;
    private JTextField NICKFIELD;
    private JTextArea textArea1;
    private JLabel snake;
    private JLabel pong;
    private JLabel tetris;
    private JLabel socoban;
    private JButton snakeplay;
    private JButton tetrisplay;
    private JButton pongplay;
    private JButton socobanplay;
    private JButton LOGINButton;
    private JTextArea textArea2;
    private JButton playMusicButton;
    private JButton stopMusicButton;
    private JButton EXITButton;
    private JTextArea textArea3;
    private Sound sound;
    private boolean islogged;
    private String Nick;
    FileWrite fileWrite;

    public Platform() {

        fileWrite = new FileWrite();
        islogged = false;
        sound = new Sound ();

     //   DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
        textArea1.setLineWrap ( true );
        textArea1.setEditable ( false );
        textArea2.setEditable ( false );
        textArea3.setEditable ( false );

        textArea2.setLineWrap ( true );
        textArea3.setLineWrap ( true );
        textArea1.setMaximumSize ( new Dimension ( 400 , 300 ) );
        textArea2.setMaximumSize ( new Dimension ( 400 , 300 ) );
        textArea3.setMaximumSize ( new Dimension ( 400 , 300 ) );
        textArea1.setWrapStyleWord ( true );
///////////////////////////// FILE READING AND SETTING INTO TEXTAREA
        try {
            FileReader fr = new FileReader ( "tetris.txt" );
            BufferedReader br = new BufferedReader ( fr );

            String str;
            while ((str = br.readLine ()) != null) {
                textArea1.setText ( textArea1.getText () + "\n" + str );
            }
            br.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        try {
            FileReader fr = new FileReader ( "pong.txt" );
            BufferedReader br = new BufferedReader ( fr );

            String str;
            while ((str = br.readLine ()) != null) {
                textArea3.setText ( textArea3.getText () + "\n" + str );
            }
            br.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
        try {
            FileReader fr = new FileReader ( "snake.txt" );
            BufferedReader br = new BufferedReader ( fr );

            String str;
            while ((str = br.readLine ()) != null) {
                textArea2.setText ( textArea2.getText () + "\n" + str );
            }
            br.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
/////////////////////////////////////////
       // textArea1.setEditable ( false );

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nick = NICKFIELD.getText();
                if(nick.length() < 3  ){
                    JOptionPane.showMessageDialog(null , "At Least 3 Characters");
                    islogged = false;
                }
                else if( nick.length () > 6){
                    JOptionPane.showMessageDialog(null , "At Most 8 Characters");
                    islogged = false;
                }
                else if(nick.contains ( " " )){
                    JOptionPane.showMessageDialog(null , "Nick cannot contain a space");
                    islogged = false;
                }
                else{
                    JOptionPane.showMessageDialog(null , "Hello" + " " + nick);
                    islogged = true;
                    //Player player = new Player ( nick );
                    Nick = nick;

                 /* Date date = new Date (  );

                    textArea1.setText ( textArea1.getText () + "\n" + nick + " " + 20 + " points "  + date );

                    int count = textArea1.getLineCount ();*/

                    //  textArea1.insert ( nick , 20 );
                }
            }
        });
        pongplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(islogged == true) {

                    Date date = new Date();
                    String str = Nick + " " +   date.toString() ;
                            textArea3.setText(textArea3.getText() + "\n" + str);
                            fileWrite.writefile(str , "pong.txt");
                    Pong pong = new Pong();
                    pong.doSth();
                }
                else {
                    JOptionPane.showMessageDialog(null , "You are not Registered");
                }
            }
        });
        tetrisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(islogged) {
                    Date date = new Date();
                    String str = Nick + " " + date.toString();
                    textArea1.setText(textArea1.getText() + "\n" + str);
                    fileWrite.writefile(str , "tetris.txt");
                    Tetris tetris = new Tetris();
                }
                else {
                    JOptionPane.showMessageDialog(null , "You are not Registered");
                }
            }

        });
        snakeplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(islogged ) {
                    Date date = new Date();
                    String str = Nick + " " + date.toString();
                    textArea2.setText(textArea2.getText() + "\n" + str);
                    fileWrite.writefile(str , "snake.txt");
                    Snake snake = new Snake();
                    snake.dosth();
                }
                else {
                    JOptionPane.showMessageDialog(null , "You are not Registered");
                }
            }
        });
        socobanplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  SokobanLauncher soko = new SokobanLauncher();
              //  soko.run();
            }
        });
        //////////////////////////////////////
        EXITButton.addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit ( 0 );
            }
        } );
        stopMusicButton.addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.stop ();
            }
        } );
        playMusicButton.addActionListener ( new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.start ();
            }
        } );
        //////////////////////////////////////////////////////////////
    }

    public void startuj(){
        JFrame frame = new JFrame("Platform");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1000 , 600);
        frame.setVisible(true);
        frame.add(main_panel);
    }


    public static void main(String[] args){
        Platform platform = new Platform();
        platform.startuj();
    }

    private void createUIComponents() {
        pong = new JLabel(new ImageIcon("pong.png"));
        snake = new JLabel(new ImageIcon("snake.png"));
        tetris = new JLabel(new ImageIcon("tetris.png"));
        socoban = new JLabel(new ImageIcon("socoban.png"));

    }
}
