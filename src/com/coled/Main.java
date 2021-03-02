package com.coled;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void accessKeyCode(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN) {

        }else if (key == KeyEvent.VK_UP) {

        }else if (key == KeyEvent.VK_LEFT) {

        }else if (key == KeyEvent.VK_RIGHT) {

        }
    }


    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/coled/intro.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(Colors.RED + line + Colors.RESET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new KeyListenerTester("Key Listener");
    }
}
