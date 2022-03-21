import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
       Zad3();



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

    private static void Zad3(){
        int[] array = new int[]{4, 5, 7, 11, 12, 15, 15, 21, 40, 45 };
        int index = SearchIndex(array, 11); // we want to find index for 11

        System.out.println(index);
    }

    private static int SearchIndex(int[] array, int value){
        int index = 0;
        int limit = array.length - 1;
        while (index <= limit) {
            int point = (int) Math.ceil((index + limit) / 2);
            int entry = array[point];
            if (value > entry) {
                index = point + 1;
                continue;
            }
            if (value < entry) {
                limit = point - 1;
                continue;
            }
            return point;
        }
        return -1;
    }
}


