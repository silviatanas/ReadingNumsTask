package task;

import java.io.*;
import java.util.Arrays;

public class ReadingNumsTask {
    public static void main(String[] args) {

        File file = new File("numsFile.txt");

        // Saw this try/catch block while looking into files, thought it would be nice to have it
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("File created successfully!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error in creating file.");
            e.printStackTrace();
        }

        BufferedReader fileData = null;
        try {
            // If my file path is written correctly this should be fine
            fileData = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Error in finding file.");
            e.printStackTrace();
        }

        // File goes into a String
        String readingFile = null;
        try {
            readingFile = fileData.readLine();
            // need to close?
        } catch (IOException e) {
            System.out.println("Error in reading file.");
            e.printStackTrace();
        }

        // String into String array
        String[] readingFileArr = readingFile.split(";"); // separating the string to arr elements

        // Making an actual array of numbers and printing
        int[] numbers = new int[readingFileArr.length];

        for (int i = 0; i < readingFileArr.length; i++) {
            numbers[i] = Integer.parseInt(readingFileArr[i]);
        }

        System.out.println(Arrays.toString(numbers));
    }
}
