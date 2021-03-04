package com.coled;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static LinkedList<Item> inventory;

    /**
     * Function to access keycodes from the key listener
     * @param e key event
     */
    public static void accessKeyCode(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN) {

        }else if (key == KeyEvent.VK_UP) {

        }else if (key == KeyEvent.VK_LEFT) {

        }else if (key == KeyEvent.VK_RIGHT) {

        }else if (key == KeyEvent.VK_E){

        }
    }

    /**
     * Prints a file line by line
     * @param path file path
     */
    public static void printFile(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
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


    public static void main(String[] args) {
        printFile("src/com/coled/intro.txt");
        new KeyListenerTester("Key Listener");

        Item test = new Item("test", 12);
        test.getDamage();
    }
}
