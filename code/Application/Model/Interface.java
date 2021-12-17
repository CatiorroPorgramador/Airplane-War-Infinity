package Application.Model;

import java.awt.*;
import java.awt.event.*;

public class Interface {
    private boolean inspect; 

    private int hp, kills, coins;

    private Rectangle life_bar_player;
    
    public Interface() {
        inspect = false;
        life_bar_player = new Rectangle(0, 0, 100, 30);
    }

    public void render_life_bar(Graphics2D graphics) {
        graphics.fillRect(life_bar_player.x, life_bar_player.y, 
        hp, life_bar_player.height);

        // Outline
        graphics.setColor(Color.BLACK);
        graphics.draw(new Rectangle(life_bar_player.x, life_bar_player.y, life_bar_player.width, life_bar_player.height));
    
        graphics.drawString("HP: "+this.hp, life_bar_player.x, life_bar_player.y - 25);
        graphics.drawString("KILLS: "+this.kills, life_bar_player.x, life_bar_player.y - 15);
        graphics.drawString("COINS: "+this.coins, life_bar_player.x, life_bar_player.y - 5);
    }

    public void render(Graphics2D graphics) {
        graphics.setColor(Color.green);
        
        if (inspect)
            render_life_bar(graphics);
    }

    public void update(int position_x, int position_y, int hp, int kills, int coins) {
        life_bar_player.x = position_x;
        life_bar_player.y = position_y - 40;

        this.hp = hp;
        this.kills = kills;
        this.coins = coins;
    }
    
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_H) {
            inspect = !inspect;
        }
    }
}
