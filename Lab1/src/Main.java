import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    private static int[] CRC_TABLE = new int[256];

    public static void main(String[] args) {
       Zad8();
    }

    private static String Zad1(){
        File file = new File("plik.txt");
        try (
                FileInputStream inputStream = new FileInputStream(file);
                InputStreamReader fileReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        {
            StringBuffer stringBuilder = new StringBuffer();
            char[] buffer = new char[(int) file.length()];
            while (true) {
                int count = fileReader.read(buffer, 0, buffer.length);

                if (count == -1) {
                    break;
                }

                stringBuilder.append(buffer, 0, count);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
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

    private static void Zad4(){
        for (int i = 0; i < 256; ++i) {
            int code = i;
            for (int j = 0; j < 8; ++j) {
                code = ((code & 0x01) == 1 ? 0xEDB88320 ^ (code >>> 1) : (code >>> 1));
            }
            CRC_TABLE[i] = code;
        }

        String result = Integer.toBinaryString(crc32("This is example text ..."));

        System.out.println(Long.parseLong(result,2));
    }
    private static int crc32(String text){
        int crc = -1;
        for (int i = 0; i < text.length(); ++i) {
      	int code = (int) text.charAt(i);
            crc = CRC_TABLE[(code ^ crc) & 0xFF] ^ (crc >>> 8);
        }
        return (-1 ^ crc) >>> 0;
    }


    private static void Zad5(){
        ZonedDateTime local = ZonedDateTime.now();

        System.out.println(local);
        System.out.println("Obecny lokalny czas: " + local.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)));



        ZonedDateTime global = ZonedDateTime.now(ZoneOffset.UTC);
        System.out.println("Obecny globalny czas: " + global.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL)));




    }
    private static void Zad6 (){
        String str = Zad1();
        String[] lines = str.split("\r?\n");

        for (int i=0; i< lines.length; i++){
            System.out.println(i+1 + " " + lines[i]);
        }
    }
    private static void Zad7(){

        ObjectMapper objectMapper = new ObjectMapper();

        User7 userObject = new User7("John", 21);
        String userJson = null;
        try {
            userJson = objectMapper.writeValueAsString(userObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(userJson); // {"name":"John","age":21}

    }
    private static void Zad8(){

        ObjectMapper objectMapper = new ObjectMapper();

        String userJson = "{\"name\":\"John\",\"age\":21}";
        User8 userObject = null;
        try {
            userObject = objectMapper.readValue(userJson, User8.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(userObject.getName()); // John
        System.out.println(userObject.getAge());  // 21

    }
}


