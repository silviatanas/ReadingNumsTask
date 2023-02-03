package readwrite;

import writing.WritingRandomInts;

import java.io.*;
import java.util.HashMap;

public class ReadWriteHashMap extends WritingRandomInts {
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

        // Making an array of numbers
        int[] numsArr = new int[readingFileArr.length];

        for (int i = 0; i < readingFileArr.length; i++) {
            numsArr[i] = Integer.parseInt(readingFileArr[i]);
        }
        
        // START
        startTime = System.nanoTime();

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
//        for (Integer key: countingNums.keySet()) {
//            System.out.format("\nNumber: %-10d Occurs: %-10d", key, countingNums.get(key));
//        }

        // END
        endTime = System.nanoTime();
        finalTime = endTime - startTime;

        System.out.println("TOTAL TIME: " + finalTime / 1_000_000_000 + " seconds");
    }
}
