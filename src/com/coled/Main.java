package com.coled;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static LinkedList<Item> inventory;
    public static int playerHealth;

    /**
     * Prints an array to the console
     * @param arr array to print
     */
    public static void printArrayString(String[] arr) {
        for (String i : arr){System.out.print(i);}
    }

    /**
     * Prints a series of newlines to the console to "clear" it.
     */
    public static void clearConsole() {
        System.out.println(System.lineSeparator().repeat(50));
    }

    //Use modes to determine what to do with player input
    public static int currentMode = 0;

    /**
     * Function to access keycodes from the key listener
     * @param e key event
     */
    public static void accessKeyCode(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN) {
            Player.updatePosition("s");
        }else if (key == KeyEvent.VK_UP) {
            Player.updatePosition("n");
        }else if (key == KeyEvent.VK_LEFT) {
            Player.updatePosition("w");
        }else if (key == KeyEvent.VK_RIGHT) {
            Player.updatePosition("e");
        }else if (key == KeyEvent.VK_E){
            //TODO ?????
        }
        Tile standingTile = Map.getTile(Player.getPosition()[0], Player.getPosition()[1], true);
        if(standingTile.isEvent()){
            //Execute event....
        }else {
            System.out.println(Map.getMapString());
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

    }


    public static void main(String[] args) {
        printFile("src/com/coled/intro.txt");
        new KeyListenerTester("Key Listener");

        //Below is a example for how to generate a map
        Map.createNewMap("Basic", 10,10, 0);
        //To get the current frame of the map for printing, call this function
        System.out.println(Map.getMapString());

        Enemy test = new Sheep(0,0);
        Battle battle = new Battle(5, test);
        battle.battleInit();
        battle.enemyAttack();
    }
}
