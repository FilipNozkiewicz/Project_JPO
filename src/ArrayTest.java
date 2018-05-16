import java.util.ArrayList;

public class ArrayTest {

    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<Integer> (  );

        arr.add ( 2 );
        arr.add ( 4 );
        arr.add ( 3 );

        arr.set ( 0 , 10 );

        for(Integer i : arr){
            System.out.println ("Elements: " + i );
        }
    }
}
