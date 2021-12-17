package Application.Model;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class Enemy {
    private int x, y, speed;
    private int width, height;

    private boolean visible;

    private Image texture;
    private ArrayList<Image> texture_list;

    private int damage;

    private Random random;

    public Enemy(int position_x, int position_y) {
        visible = true;

        this.x = position_x;
        this.y = position_y;

        speed = 3;

        texture_list = new ArrayList<Image>();

        random = new Random();

        load();
        define_type();
    }

    public void load() {
        texture = new ImageIcon("data/Ships/ship_0005.png").getImage();

        texture_list.add(texture);
        texture_list.add(new ImageIcon("data/Ships/ship_0001.png").getImage());
        texture_list.add(new ImageIcon("data/Ships/ship_0009.png").getImage());

        width  = 40;
        height = 78;
    }

    public void define_type() {
        int type_int = random.nextInt(3);

        texture = texture_list.get(type_int);

        damage = random.nextInt(50) + 10; // 10 - 60
    }

    public void update() {
        x -= speed;
        if (x < -width)
            kill();
    }

    public void kill() {
        this.visible = false;
    }

    // Getters and Setters
    public int get_x() {
        return this.x;
    }

    public int get_y() {
        return this.y;
    }

    public int get_width() {
        return this.width;
    }

    public int get_height() {
        return this.height;
    }

    public int get_damage() {
        return this.damage;
    }

    public boolean is_visible() {
        return this.visible;
    }

    public Image get_texture() {
        return this.texture;
    }

    public Rectangle get_rect() {
        return new Rectangle(x+16, y, width, height);
    }
    
}