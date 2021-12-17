package Application.Model;

import java.awt.*;

import javax.swing.ImageIcon;

public class Shot {
    private int x, y, speed;
    private int width, height;

    private boolean visible;

    private Image texture;

    public Shot(int position_x, int position_y) {
        visible = true;

        this.x = position_x;
        this.y = position_y;

        width = 10;
        height = 10;

        speed = 20;
    }

    public void load() {
        texture = new ImageIcon("data/Tiles/tile_0000.png").getImage();
    }

    public void update() {
        x += speed;
        if (x > 640)
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

    public boolean is_visible() {
        return this.visible;
    }

    public Image get_texture() {
        return this.texture;
    }

    public Rectangle get_rect() {
        return new Rectangle(x, y, width, height);
    }
    
}
