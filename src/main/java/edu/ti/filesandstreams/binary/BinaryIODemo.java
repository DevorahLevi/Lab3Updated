package edu.ti.filesandstreams.binary;

import edu.ti.filesandstreams.dataobject.Species;

import java.io.*;
import java.util.Scanner;

public class BinaryIODemo {
    //TODO -- read species data from an input file
    static Species[] initSpecies;

    public static void instantiateSpecies() {
        String fileName = "src/main/resources/species.txt";

        Scanner inputStream = null;
        try {
            File file = new File(fileName);
            inputStream = new Scanner(file);
            initSpecies = new Species[3]; //*****How do I declare this for a file that I dont know how many lines there will be,
                                                // and dont know how many species objects will be created?
            int count = -1;
            while (inputStream.hasNextLine()) {
                count++;
                String line = inputStream.nextLine();
                String[] dataArray = line.split(",");
                String name = dataArray[0];
                int population = Integer.parseInt(dataArray[1]);
                double growthRate = Double.parseDouble(dataArray[2]);
                Species species = new Species(name, population, growthRate);
                initSpecies[count] = species;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void main (String [] args) {
        BinaryIODemo.instantiateSpecies();
        //TODO -- get the fileName from a command line argument
        String resourceFolder = "src/main/resources";
        String fileName = resourceFolder + "/" + args[0];
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream (new FileOutputStream(fileName))){
            objectOutputStream.writeObject (initSpecies);
        } catch (FileNotFoundException e) {
            System.out.println ("FileNotFoundException thrown writing to file " + fileName + ":" + e.getMessage());
        } catch (IOException e) {
            System.out.println ("IOException thrown writing to file " + fileName + ":" + e.getMessage());
        }
        System.out.println ("Array written to file " + fileName + " and file is closed.");
        System.out.println ("Open the file for input and echo the array.");

        Species[] readFromFileSpecies = null;
        try (ObjectInputStream inputStream =
                new ObjectInputStream (
                    new FileInputStream(fileName))) {
            readFromFileSpecies = (Species[]) inputStream.readObject ();
        } catch (Exception e) {
            System.out.println ("Error reading file " + fileName + ": " + e.getMessage());
        }

        System.out.println("The following were read from the file " + fileName + ":");
        assert readFromFileSpecies != null;
        for (Species readFromFileSpecy : readFromFileSpecies) {
            System.out.println(readFromFileSpecy.toString());
        }
    }
}
