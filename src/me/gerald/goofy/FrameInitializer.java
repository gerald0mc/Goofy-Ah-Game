package me.gerald.goofy;

import javax.swing.*;

public class FrameInitializer {
    public static Goofy gamePanel;

    public static void main(String... args) {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Goofy Ah Game");
        gamePanel = new Goofy();
        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
