package Application.Model;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.io.*;

public class GameOver extends JPanel {
    Font font;

    public GameOver() {
        load_font();

        addKeyListener(new key_adapter());
    }

    public void load_font() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("data/Pixel.ttf")).deriveFont(12f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        load_font();
        Graphics2D graphics = (Graphics2D) g;

        // Background
        graphics.drawImage(new ImageIcon("data/Menus/Game Over.png").getImage(), 0, 0, 640, 480, null);

        graphics.dispose();
    }

    private class key_adapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {
                System.out.println("Play Again");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
    }
}