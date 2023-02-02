package writing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        System.out.println("Operations finished");
    }
}
