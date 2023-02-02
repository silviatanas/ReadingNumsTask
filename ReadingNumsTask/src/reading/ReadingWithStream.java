package reading;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReadingWithStream {
    public static void main(String[] args) {

        File file = new File("numsFile.txt");

        // Creating file, checking if a file already exists, reading file
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("File created successfully!\nProceeding to read file...");
            } else {
                System.out.println("File already exists.\nProceeding to read file...");
            }
        } catch (IOException e) {
            System.out.println("Error in creating file.\nEnding program.");
            e.printStackTrace();
        }

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

        // Using stream
        Arrays.stream(readingFileArr)
                // Elements are used as unique keys in a map
                // List of values collects every appearance of a key
                .collect(Collectors.groupingBy(i -> i))
                // Printing with values size to get number of collected elements
                .forEach((k, v) -> System.out.format("\nNumber: %-10s Occurs: %-10d", k, v.size()));

    }
}
