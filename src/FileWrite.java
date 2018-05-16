import java.io.*;

public class FileWrite{

    public void writefile(String string , String file) {
        File fl = new File(file);

        try {
            FileWriter writer = new FileWriter(fl, true);
            BufferedWriter buffer = new BufferedWriter(writer);
            PrintWriter printWriter = new PrintWriter(buffer);

            printWriter.println(string);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
