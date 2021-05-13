import javax.swing.*;
import java.awt.*;
public class Obstacles {
    private int x,y;
    private int height, width;
    //Declare images, basic enemy, bat, heart
    Image basicEnemy, bat, heart;

    //Our obstacles are currently rectangles. X and Y represent where they are located on the game window, width and height are how big the obstacles are
    public Obstacles(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //Get images of the basic enemy, bat, and heart
        basicEnemy = new ImageIcon("basicEnemy.gif").getImage();
        bat = new ImageIcon("Bat.gif").getImage();
        heart = new ImageIcon("Heart.gif").getImage();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //The update function for our basic enemy. Moves the enemy to the left by 11 pixels
    public void updateBasicEnemy() {
        this.x -= 11;
    }

    //Bats move to the left by 25 pixels
    public void updateBat(){
        this.x -= 25;
    }

    //Hearts move from right to left by 11 pixels
    public void updateHeart(){
        this.x -= 11;
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

    //Paint function for heart
    public void paintHeart(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(heart, x, y, 40,40,null);
    }

    //Shift function for obstacles. When the obstacle reaches the left side of the screen, shift/move the obstacle over to the right to make it look like the game is infinite
    public void shift(){
        if (this.x < 0){
            this.x = (int) (800+Math.random()*200);
            this.y = (int) (Math.random()*400);
        }
    }
}
