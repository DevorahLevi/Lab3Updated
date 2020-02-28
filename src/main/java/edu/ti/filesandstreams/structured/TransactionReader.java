package edu.ti.filesandstreams.structured;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TransactionReader {
    public static void main(String[] args) {
        //TODO -- get the fileName from a command line argument
        String resourceFolder = "src/main/resources";
        String fileName = resourceFolder + "/" + args[0];
        Scanner inputStream = null;

        String outputFileName = "src/main/resources/" + args[1];
        PrintWriter printWriter = null;

        try {
            //create Java object that represents file
            File file = new File(fileName);

            //scanner knows how to read from the input source
            inputStream = new Scanner(file);

            // initialize variable we will use to record total sales
            double total = 0;

            // Read the header line; this is just for user, not used by program
            String line = inputStream.nextLine();
            System.out.println("Header line is: " + line);

            // Read the rest of the file line by line
            while (inputStream.hasNextLine()) {
                //input file is known as a "csv" -- comma separated values
                // each line contains SKU,Quantity,Price,Description
                line = inputStream.nextLine();

                // Turn the string into an array of strings
                // the split method of String creates an array of the parts of the
                // string split by a delimiter characater
                String[] dataArray = line.split(",");

                // convert the elements of the array to data
                String SKU = dataArray[0];
                int quantity = Integer.parseInt(dataArray[1]);
                double price = Double.parseDouble(dataArray[2]);
                String description = dataArray[3];

                // Output the parsed line of input
                //TODO -- write to output file, get filename from command line

                printWriter = new PrintWriter(outputFileName);
                printWriter.printf("Sold %d of %s (SKU: %s) at $%1.2f each.\n",
                        quantity, description, SKU, price);
                // Compute total
                total += quantity * price;
            }
            printWriter.printf("Total sales: $%1.2f\n", total);
            System.out.println("Transaction details have been printed to " + outputFileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + fileName + ": " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with input from file " + fileName + ": " + e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}