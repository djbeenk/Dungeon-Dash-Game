import java.awt.*;

public class Player {
    private int x, y;
    private int radius;

    public Player(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public void jump() {
        this.y -= 45;
    }

    public void jumpDown() {
        this.y += 45;
    }

    public void paint(Graphics g) {
        int diameter = radius * 2;
        g.setColor(Color.black);
        g.fillOval(x - radius, y - radius, diameter, diameter);
    }
}
