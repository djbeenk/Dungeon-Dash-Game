import javax.swing.*;
import java.awt.*;
public class Obstacles {
    private int x,y;
    private int height, width;
    private int xSpeed, ySpeed;
    //Declare images, basic enemy and bat
    Image basicEnemy, bat;

    //Our obstacles are currently rectangles. X and Y represent where they are located on the game window, width and height are how big the obstacles are
    public Obstacles(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //Get images of the basic enemy and bat
        basicEnemy = new ImageIcon("basicEnemy.gif").getImage();
        bat = new ImageIcon("Bat.gif").getImage();
    }

    //Not using below methods currently, may use it in future
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
    //Not using above methods currently, may use it in future

    //The update function for our obstacles. Moves the obstacles to the left by 11 pixels
    public void update() {
        this.x -= 11;
    }

    public void updateBat(){
        this.x -= 25;
    }

    //Paint function for the basic enemy
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(basicEnemy, x, y, 60, 30,null);
    }

    //Paint function for bat
    public void paintBat(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(bat, x, y, 40, 40,null);
    }

    //Shift function for obstacles. When the obstacle reaches the left side of the screen, shift/move the obstacle over to the right to make it look like the game is infinite
    public void shift(){
        if (this.x < 0){
            this.x = (int) (800+Math.random()*200);
            this.y = (int) (Math.random()*400);
        }
    }
}
