import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
       Zad2();



    }
    private static void Zad1(){
        File file = new File("plik.txt");
        try (
                FileInputStream inputStream = new FileInputStream(file);
                InputStreamReader fileReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) // UTF-8
        {
            char[] buffer = new char[(int) file.length()];
            while (true) {
                int count = fileReader.read(buffer, 0, buffer.length); // where count variable stores number of read characters
                if (count == -1) {
                    break;
                }
                if (count > 0) {
                    String text = new String(buffer, 0, count);
                    System.out.print(text);
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void Zad2(){
        File file = new File("zadanie2.txt");
        try (
                FileOutputStream outputStream = new FileOutputStream(file);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(System.in))
               )
        {
            String text= reader.readLine();
            outputStream.write(text.getBytes(StandardCharsets.UTF_8));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

