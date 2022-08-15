package one_week_preparation_kit.day_a.time_conversion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Result {


    public static String timeConversion(String serializedDate) {
        SimpleDateFormat sourceFormat = new SimpleDateFormat("hh:mm:ssaa");
        SimpleDateFormat targetFormat = new SimpleDateFormat("HH:mm:ss");
        
        Date date;
        try {
            date = sourceFormat.parse(serializedDate);
            return targetFormat.format(date);
        } catch (ParseException e) {
            return null;
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.timeConversion(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
