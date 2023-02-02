package readwrite;

import writing.WritingRandomInts;

import java.io.*;
public class ReadWriteCounter extends WritingRandomInts {
    public static void main(String[] args) {

        long endTime, finalTime;
        long[] startTime = {0};

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

        // Using counter in array
        int counter;

        label:
        for (int i = 0; i < numsArr.length; i++) {
            counter = 0;

            // Avoiding repeats:
            // Checking if current number appeared earlier in array
            // If it did we move on to the next number in the array
            for (int check = i - 1; check >= 0; check--) {
                if (numsArr[i] == numsArr[check]) {
                    continue label;
                }
            }

            // If number appears again the counter is incremented
            for (int j = 0; j < numsArr.length; j++) {
                if (numsArr[i] == numsArr[j]) {
                    counter++;
                }
            }
            // Printing results
            //System.out.format("\nNumber: %-20d Occurs: %-10d", numsArr[i], counter);
        }

        // END
        endTime = System.nanoTime();
        finalTime = endTime - startTime[0];

        System.out.println("TOTAL TIME: " + finalTime / 1_000_000_000 + " seconds");
    }
}
