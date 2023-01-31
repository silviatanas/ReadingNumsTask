package writing;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class WritingRandomInts {
    public static void main(String[] args) {
        
        // Filepath user input
        Scanner inputPath = new Scanner(System.in);
        System.out.println("Enter file path:");
        String filePath = inputPath.nextLine();

        File file = new File(filePath);
        FileWriter writer = null;

        // Inputting file size
        Scanner sizeInput = new Scanner(System.in);
        System.out.println("Enter file size (MB):");
        int megabytes = sizeInput.nextInt();
        int bytes = megabytes * 1024 * 1024;

        Scanner selection = new Scanner(System.in);
        String select;

        // Random ints initialization
        Random random = new Random();
        int num;

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

                    if (select.toLowerCase().equals("n")) {
                        System.out.println("Ending program.");
                        System.exit(0);
                    }
                } while (!select.equalsIgnoreCase("y"));

                System.out.println("Proceeding to write into file...");
            }
        } catch (IOException e) {
            System.out.println("Error in creating file.\nEnding program.");
            e.printStackTrace();
        }

        try {
             writer = new FileWriter(file);
        } catch (IOException e) {
            System.out.println("Error in writing in file.\nEnding program.");
            e.printStackTrace();
        }

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

        System.out.println("Operation finished!");
    }
}
