package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

	
	// Reads a value from the properties file using the provided key
    public static String readDataFromPropertyFile(String path, String key) {
        File file = new File(path);
        FileInputStream fi = null;
        Properties propFile = new Properties();
        String data = null;

        try {
            fi = new FileInputStream(file);
            propFile.load(fi);
            // Retrieve the value associated with the key, or return the default if not found
            data = propFile.getProperty(key, "default-value");
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found at the provided path: " + path);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while loading the properties file.");
            e.printStackTrace();
        }
        return data;
    }

    // Writes a key-value pair into the properties file
    public static void writeDataToPropertyFile(String path, String key, String value) {
        Properties propFile = new Properties();
        propFile.setProperty(key, value);
        FileOutputStream fo = null;
        File file = new File(path);

        try {
            fo = new FileOutputStream(file);
            propFile.store(fo, "Adding new property with value");
            fo.close();
            System.out.println("Property written: " + key + " = " + value);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found or inaccessible.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while writing to the properties file.");
            e.printStackTrace();
        }
    }

    // Returns the number of key-value pairs in the properties file
    public static int getSize(String path) {
        File file = new File(path);
        FileInputStream fi = null;
        Properties propFile = new Properties();
        int size = 0;

        try {
            fi = new FileInputStream(file);
            propFile.load(fi);
            size = propFile.size();  // Returns the number of properties in the file
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while loading the properties file.");
            e.printStackTrace();
        }

        return size;
    }
}


























	
	
	
	
	
	
	
	
	
	
	
	
	