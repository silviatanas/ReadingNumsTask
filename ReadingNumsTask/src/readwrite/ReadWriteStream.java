package readwrite;

import writing.WritingRandomInts;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReadWriteStream extends WritingRandomInts {
    public static void main(String[] args) {

        long startTime, endTime, finalTime;

        File file = writing(startTime);

        System.out.println("--------------------------------------------");
        System.out.println("Creation operations finished");
        System.out.println("Reading file...");
        System.out.println("--------------------------------------------");

        BufferedReader fileData = null;
        try {
            fileData = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Error in finding file.\nEnding program.");
            e.printStackTrace();
        }

        // File goes into a String
        String readingFile = null;
        try {
            readingFile = fileData.readLine();
        } catch (IOException e) {
            System.out.println("Error in reading file.\nEnding program.");
            e.printStackTrace();
        }

        // String into String array
        String[] readingFileArr = readingFile.split(";"); // separating the string to arr elements
        
        // START
        startTime = System.nanoTime();

        // Using stream
        Arrays.stream(readingFileArr)
                // Elements are used as unique keys in a map
                // List of values collects every appearance of a key
                .collect(Collectors.groupingBy(i -> i));
                // Printing with values size to get number of collected elements
                //.forEach((k, v) -> System.out.format("\nNumber: %-10s Occurs: %-10d", k, v.size()));

        // END
        endTime = System.nanoTime();
        finalTime = endTime - startTime[0];

        System.out.println("TOTAL TIME: " + finalTime / 1_000_000_000 + " seconds");
    }
}
