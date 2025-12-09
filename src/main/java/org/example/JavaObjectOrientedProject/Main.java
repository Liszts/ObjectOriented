package org.example.JavaObjectOrientedProject;

import javax.swing.*;

public class Main {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuizApp app = new QuizApp();
            app.setVisible(true);
        });
    }
}
