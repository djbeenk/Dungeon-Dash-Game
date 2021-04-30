import javax.swing.*;
import java.awt.*;

public class Player {
    private int x, y;
    private int radius;
    Image player;

    //Player character is currently a sphere, x and y are locations while radius is for the shape
    public Player(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        player = new ImageIcon("player.gif").getImage();
    }

    //Not using below functions currently, may use in future
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
    //Not using above functions currently, may use in future

    //Jump function for player character. Every time player clicks up, moves player up 15 pixels
    public void jump() {
        this.y -= 15;
    }

    //Jump down function. Everytime player clicks down arrow, moves player down 15 pixels
    public void jumpDown() {
        this.y += 15;
    }

    //Paint function.
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(player, x, y, 50, 50,null);
    }
}
