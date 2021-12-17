package Application;

import javax.swing.*;

import Application.Model.Game;
import Application.Model.GameOver;

public class App extends JFrame {
    private static final double version = 0.2;

    private static final String title = "Simple Infinity Java Edition - " + version;
    
    public App() {
        add(new Game());

        setTitle(title);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
