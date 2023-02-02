package readwrite;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ReadWriteCounter {
    public static void main(String[] args) {

        long startTime, endTime, finalTime;

        // Filepath user input
        Scanner inputPath = new Scanner(System.in);
        System.out.println("Enter file path:");
        String filePath = inputPath.nextLine();

        File file = new File(filePath);
        FileWriter writer = null;

        // Inputting file size
        Scanner sizeInput = new Scanner(System.in);
        int megabytes;

        while (true) {
            System.out.println("Enter file size up to 10GB (input is in MB):");
            if (sizeInput.hasNextInt()) {
                megabytes = sizeInput.nextInt();
                if (megabytes <= 10_240) {
                    break;
                }
            } else {
                System.out.println("It appears you haven't put in a number. Try again.");
                sizeInput.nextLine();
            }
        }

        int bytes = megabytes * 1024 * 1024;

        Scanner selection = new Scanner(System.in);
        String select;

        // Random ints initialization
        Random random = new Random();
        int num;

        // START
        startTime = System.nanoTime();

        // Creating file, checking if a file already exists, writing/overriding file
        try {
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("File created successfully!\nProceeding to write file...");
            } else {
                System.out.println("File already exists.");

                do {
                    System.out.println("Would you like to override file? (Y/N)");
                    select = selection.nextLine();

                    if (select.equalsIgnoreCase("n")) {
                        System.out.println("Ending program.");
                        System.exit(0);
                    }
                } while (!select.equalsIgnoreCase("y"));

                System.out.println("Proceeding to write into file...");

                writer = new FileWriter(file);

                // Filling file with random ints separated by ;
                while (file.length() <= bytes) {
                    num = random.nextInt();
                    try {
                        writer.write(num + ";");
                    } catch (IOException e) {
                        System.out.println("Error in writing in file.\nEnding program.");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something sure isn't right.\nEnding program.");
            e.printStackTrace();
        }

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
        finalTime = endTime - startTime;

        System.out.println("TOTAL TIME: " + finalTime / 1_000_000_000 + " seconds");
    }
}