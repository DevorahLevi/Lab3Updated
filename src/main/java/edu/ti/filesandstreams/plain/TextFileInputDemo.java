package edu.ti.filesandstreams.plain;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TextFileInputDemo {
    public static void main(String[] args) {
        //get your home folder location from the system
        String homeFolder = System.getenv("HOMEPATH");

        //get the file from your Documents folder
        String fileName = homeFolder + "/Box/Java/Lab3Updated/src/main/resources/" + args[0];

        //let the user know what we are about to do
        System.out.println("The file " + fileName + "\ncontains the following lines:\n");

        //the Scanner knows how to read from a file (among other things)
        Scanner inputStream = null;
        try {
            //the File class knows how to negotiate the file system to access data
            File file = new File(fileName);
            inputStream = new Scanner(file);
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            //we'll only get here if there was an error opening the file
            System.out.println("Error opening the file " + fileName + ": " + e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}