import java.awt.*;

public class Obstacles {
    private int x,y;
    private int height, width;
    private int xSpeed, ySpeed;

    public Obstacles(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int i) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void update() {
        this.x -= 11;
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
    }

    public void shift(){
        if (this.x < 0){
            this.x = (int) (800+Math.random());
            this.y = (int) (Math.random()*400);
        }
    }
}
