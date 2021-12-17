package Application.Model;


import java.awt.*;
import java.awt.event.*;

import java.util.*;

import javax.swing.ImageIcon;

public class Player {
    private int dx, dy, speed;
    private int hp, kills, coins;

    Rectangle rect;

    private Image texture;

    private ArrayList<Shot> shot_list;

    public Player() {

        rect = new Rectangle(80, 180, 80, 80);

        this.hp = 100;
        this.kills = 0;
        this.coins = 0;

        shot_list = new ArrayList<Shot>();
    }

    public void load() {
        texture = new ImageIcon("data/Ships/ship_0012.png").getImage();

        speed = 5;
    }

    public void update() {
        rect.x += dx;
        rect.y += dy;
    }

    public void shoot() {
        int px = (int) rect.getX();
        int py = (int) rect.getY();

        int w = (int) rect.getWidth();
        int h = (int) rect.getHeight();

        this.shot_list.add(new Shot(px+(w/2)-8, py+(h/2)-8));
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN)
            dy = speed;
        
        else if (key == KeyEvent.VK_UP)
            dy = -speed;
        
        else if (key == KeyEvent.VK_LEFT)
            dx = -speed;

        else if (key == KeyEvent.VK_RIGHT)
            dx = speed;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_DOWN)
            dy = 0;
        
        else if (key == KeyEvent.VK_UP)
            dy = 0;
        
        if (key == KeyEvent.VK_LEFT)
            dx = 0;

        else if (key == KeyEvent.VK_RIGHT)
            dx = 0;

        if (key == KeyEvent.VK_SPACE)
            shoot();
    }   

    // Getters
    public int get_hp() {
        return this.hp;
    }

    public int get_kills() {
        return this.kills;
    }

    public int get_coins() {
        return this.coins;
    }

    public Image get_texture() {
        return this.texture;
    }

    public ArrayList<Shot> get_shot_list() {
        return this.shot_list;
    }

    public Rectangle get_rect() {
        return this.rect;
    }

    // Setters
    public void hit(int damage) {
        this.hp -= damage;
    }

    public void add_kill(int quantity) {
        this.kills += quantity;
    }

    public void add_coin(int quantity) {
        this.coins += coins;
    }
    
}
