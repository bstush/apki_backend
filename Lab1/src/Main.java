import java.io.*;

public class Main {
    public static void main(String[] args) {
       File file = new File("plik.txt");
       int length =(int)file.length();
        try ( FileInputStream stream = new FileInputStream("plik.txt");
              InputStreamReader reader = new InputStreamReader(stream);)
        {


            char[] data = new char[length];
            int readBytes = reader.read(data, 0,length );
            reader.read(data, 0, length);
            if (readBytes !=length) {
                throw new IOException("File reading error.");
            }


            String text = new String(data);
            System.out.println(text);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
