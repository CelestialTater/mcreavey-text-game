package com.coled;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/coled/intro.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(Colors.RED + line + Colors.RESET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Below is a example for how to generate a map
        Map.createNewMap("Basic", 10,10);
        //To get the current frame of the map for printing, call this function
        System.out.println(Map.getMapString());
    }
}
