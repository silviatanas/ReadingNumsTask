package task;

import java.io.*;

public class ReadingWithCounter {
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
            System.out.format("\nNumber: %-10d Occurs: %-10d", numsArr[i], counter);
        }

    }
}
