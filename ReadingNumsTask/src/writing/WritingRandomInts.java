package writing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public abstract class WritingRandomInts  {
    public static File writing() throws WritingException {

        // Filepath user input
        Scanner inputPath = new Scanner(System.in);
        boolean isDirectory = false;
        File file = null;

        // Check for directory
        while (!isDirectory) {
            System.out.println("Enter file path:");
            String filePath = inputPath.nextLine();
            file = new File(filePath);

            if (file.isDirectory()) {
                System.out.println("This is a directory, you need to specify a file.");
            } else {
                isDirectory = true;
            }
        }

        FileWriter writer;

        // Inputting file size
        Scanner sizeInput = new Scanner(System.in);
        double megabytes;

        while (true) {
            System.out.println("Enter file size up to 10GB (input is in MB):");
            if (sizeInput.hasNextDouble()) {
                megabytes = sizeInput.nextDouble();
                if (megabytes > 0 && megabytes <= 10_240) {
                    break;
                }
            } else {
                System.out.println("It appears you haven't put in a number. Try again.");
                sizeInput.nextLine();
            }
        }

        double bytes = megabytes * 1024 * 1024;

        // Override selection
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
            }
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
            writer.close();

        } catch (IOException e) {
            System.out.println("Error in writing program.\nEnding program.");
            e.printStackTrace();
            throw new WritingException(e.getMessage());
        }

        return file;
    }
}
