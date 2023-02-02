package reading;

import java.io.*;
import java.util.HashMap;

public class ReadingWithHashMap {
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

        // Making an array of numbers
        int[] numsArr = new int[readingFileArr.length];

        for (int i = 0; i < readingFileArr.length; i++) {
            numsArr[i] = Integer.parseInt(readingFileArr[i]);
        }

        // Using hashmap to count occurrences
        HashMap<Integer, Integer> countingNums = new HashMap<>();
        for (int j : numsArr) {
            if (countingNums.containsKey(j)) {
                countingNums.put(j, countingNums.get(j) + 1);
            } else {
                countingNums.put(j, 1);
            }
        }

        // Printing final results
        for (Integer key: countingNums.keySet()) {
            System.out.format("\nNumber: %-10d Occurs: %-10d", key, countingNums.get(key));
        }

    }
}
