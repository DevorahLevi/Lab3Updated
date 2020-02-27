package edu.ti.filesandstreams.plain;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileOutputDemo {
    public static void main(String[] args) {
        //gets your home folder location from the system
        String homeFolder = System.getenv("HOMEPATH");
        
        //put the file in your Documents folder
        //TODO -- get the fileName from a command line argument
        String fileName = homeFolder + "/Box/Java/Lab3Updated/src/main/resources/" + args[0];

        //The PrintWriter class allows you to write out text to a file
        PrintWriter printWriter = null;
        try {
            //you tell the PrintWriter whence to write text via the constructor
            //When you give a PrintWriter a file name, it will create the file if it does not already
            //exist, or it will truncate (make it empty) otherwise
            printWriter = new PrintWriter(fileName);

            //prompt the user for input
            System.out.println("Enter three lines of text:");

            //instantiate a Scanner to read the user's input
            Scanner keyboard = new Scanner(System.in);

            //we are starting at 1 because we are using it as a label for each line in the output file
            for (int count = 1; count <= 3; count++) {
                //read input one line at a time
                String line = keyboard.nextLine();

                //print the current count followed by a space, followed by the input
                printWriter.println(count + " " + line);
            }

            //appraise the user of the situation
            System.out.println("Those three lines were written to " + fileName);
        } catch (FileNotFoundException e) {
            //we'll only get here if there was an error opening the file
            System.out.println("Error opening the file " + fileName + ": " + e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
