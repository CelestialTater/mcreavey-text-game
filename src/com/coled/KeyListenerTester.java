package com.coled;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KeyListenerTester extends JFrame implements KeyListener {

    public KeyListenerTester(String s) {
        super(s);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel p = new JPanel();
        JLabel label = new JLabel("Press X to Quit");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Main.accessKeyCode(e);
    }

}