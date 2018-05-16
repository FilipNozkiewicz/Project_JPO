import pong.Pong;
import snake.Snake;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // FileWrite f = new FileWrite ();
        //  f.write ( "xcxcxcxcxcxc" );


        ArrayList<String> buff = new ArrayList<String> (  );
        for(int i = 0 ; i < 10 ; i++ )
        buff.add ( "" );


        try {
            FileReader fr = new FileReader ( "file.txt" );
            BufferedReader br = new BufferedReader ( fr );

            String str;
            while ((str = br.readLine ()) != null) {
                int i = 0;
                buff.set ( i , str );

                System.out.println ( str + "\n" );
                i++;
            }
            br.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }


        try {
            FileWriter fw = new FileWriter ( "file.txt" );
            PrintWriter pw = new PrintWriter ( fw );

            for(int iter = 0 ; iter < buff.size () ; iter ++) {
                pw.println ( buff.get ( iter ) );
                }
            pw.println ( "xxdddd" );
            pw.close ();
        } catch (IOException b) {
            b.printStackTrace ();
        }
        for(String s: buff){
            System.out.println ("Elements: " + s);
        }


    }
}
