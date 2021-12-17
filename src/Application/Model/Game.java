/*

To next version (0.3): 2 days
add a simple interface (texts and bar life) x
add new enemies x
create simple menus (game over, main menu) x
upgrade code
add simple menus (game over, main menu)
add sounds

add Ivete Sangalo

To version (0.4): 3 days
add aditional menus (settings, how to play, credits)
upgrade player motion
add shield

To version (0.5): 10 days
add new powers (double shot, invincible time, double coins)
add game modes (Survival, SandBox, Level)

To version (0.6): 5 days
add store
save/load game function

To version (0.7): 1-2 days
upgrade enemies

To version (0.8): 3 days
upgrade and optimize code
create effects and particles

To version (0.9): 10 days
create map generator to background
upgrade settings menu

To version (1.0) The big update: 30 days
add multiplayer mode
add animations to objects
upgrade store
create functions (broke ship with shots and fix ship)

*/

package Application.Model;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import java.io.File;
import java.awt.*;

public class Game extends JPanel implements ActionListener {

    public boolean playing;

    private Font font;

    private Image tile_water;
    private Image tile_grass;
    private Image tile_grass_2;
    private Image tile_grass_tree;

    private ArrayList<Image> tiles;
    private int background_map[][] = {
        {},
        {},
        {},
        {},
        {},
        {},
    };

    private Player player;

    ArrayList<Shot> shot_list;
    ArrayList<Enemy> enemy_list;
    private int index_spawn_enemy;
    Random random;

    private Timer timer;

    private Interface _interface;

    public Game() {
        playing = true;

        setFocusable(true);
        setDoubleBuffered(true);
        load_font();

        tiles = new ArrayList<Image>();

        tile_water = new ImageIcon("data/Tiles/tile_0042.png").getImage();
        tile_grass = new ImageIcon("data/Tiles/tile_0050.png").getImage();
        tile_grass_2 = new ImageIcon("data/Tiles/tile_0110.png").getImage();
        tile_grass_tree = new ImageIcon("data/Tiles/tile_0048.png").getImage();

        tiles.add(tile_water);
        tiles.add(tile_grass);
        tiles.add(tile_grass_2);
        tiles.add(tile_grass_tree);

        player = new Player();
        player.load();

        shot_list = player.get_shot_list();
        enemy_list = new ArrayList<Enemy>();

        addKeyListener(new key_adapter());

        random = new Random();

        timer = new Timer(5, this);
        timer.start();

        _interface = new Interface();

        index_spawn_enemy = 0;
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

    public void spawn_enemies() {
        index_spawn_enemy += 1;

        if (index_spawn_enemy > 60) {
            if (random.nextInt(12) > 5) {
                int y = random.nextInt(360);
                enemy_list.add(new Enemy(640, y));
            }

            index_spawn_enemy = 0;
        }
    }

    public void load_background() {
        for (int y=0; y < 6; y++)
            for (int x=0; x < 8; x++)
                continue;


    }

    public void render_background(Graphics2D graphics) {
        for (int y=0; y < 6; y++)
            for (int i=0; i < 8; i++)
                graphics.drawImage(tiles.get(random.nextInt(tiles.size())), i*80, y*80, 80, 80, null);
    }

    public void render_shots(Graphics2D graphics) {
        for (int i=0; i < shot_list.size(); i++) {
            Shot current_shot = shot_list.get(i);
            current_shot.load();

            graphics.drawImage(current_shot.get_texture(), current_shot.get_x(), current_shot.get_y(), this);
        }
    }

    public void render_enemies(Graphics2D graphics) {
        for (int i=0; i < enemy_list.size(); i++) {
            Enemy current_enemy = enemy_list.get(i);
            
            graphics.drawImage(current_enemy.get_texture(), current_enemy.get_x(), current_enemy.get_y(), 80, 80, this);
        }
    }

    public void update_shots() {
        shot_list = player.get_shot_list();
        for (int i=0; i < shot_list.size(); i++) {
            Shot current_shot = shot_list.get(i);
            if (current_shot.is_visible()) {
                current_shot.update();
            } else 
                shot_list.remove(i);
        }
    }

    public void update_enemies() {
        for (int i=0; i < enemy_list.size(); i++) {
            Enemy current_enemy = enemy_list.get(i);
            if (current_enemy.is_visible())
                current_enemy.update();
            else
                enemy_list.remove(i);
        }
    }

    public void update_collisions() {
        Rectangle player_rect = player.get_rect();

        for (int i=0; i < enemy_list.size(); i++) {
            Enemy current_enemy = enemy_list.get(i);
            
            // Shot Collision
            for (int a=0; a < shot_list.size(); a++) {
                Shot current_shot = shot_list.get(a);

                if (shot_list.get(a).get_rect().intersects(current_enemy.get_rect())) {
                    current_enemy.kill();
                    current_shot.kill();
                    player.add_kill(1);
                }
            }
            
            // Player Collision
            if (current_enemy.get_rect().intersects(player_rect)) {
                current_enemy.kill();
                player.hit(current_enemy.get_damage());
                player.add_kill(1);
            }
        }
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        //graphics.setFont(font);

        render_background(graphics);
    
        graphics.drawImage(
            player.get_texture(), (int) player.get_rect().getX(), (int) player.get_rect().getY(), 
            (int) player.get_rect().getWidth(), (int) player.get_rect().getHeight(), this
        );

        render_shots(graphics);
        render_enemies(graphics);

        _interface.render(graphics);

        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        spawn_enemies();
        
        player.update();

        update_shots();
        update_enemies();

        update_collisions();

        _interface.update(
            (int)player.get_rect().getX(), (int) player.get_rect().getY(), 
            player.get_hp(), player.get_kills(), player.get_coins());

        repaint();
    }

    private class key_adapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);

            _interface.keyReleased(e);
        }
    }
}
