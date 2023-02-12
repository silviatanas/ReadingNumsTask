package readwrite;

import writing.WritingException;
import writing.WritingRandomInts;

import java.io.*;
import java.util.HashMap;

public class ReadWriteHashMap extends WritingRandomInts {
    public static void main(String[] args) throws Exception {

        long startTime, endTime, finalTime;
        File file = null;
        String[] readingFileArr = new String[0];
        int[] numsArr;

        try {
            file = writing();

            // Turning file to string to array
            BufferedReader fileData;
            String readingFile;
            readingFileArr = new String[0];

            try {
                fileData = new BufferedReader(new FileReader(file));
                readingFile = fileData.readLine();
                readingFileArr = readingFile.split(";"); // separating the string to arr elements
            } catch (IOException e) {
                System.out.println("Error in reading file.\nGenerating 1MB array of numbers.");
                numsArr = new int[250_000];
            }

            // Making an array of numbers
            numsArr = new int[readingFileArr.length];

        } catch (WritingException e) {
            System.out.println("Error in writing program.");
            numsArr = new int[250_000];
        }

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
