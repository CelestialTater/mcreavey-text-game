package com.coled;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
