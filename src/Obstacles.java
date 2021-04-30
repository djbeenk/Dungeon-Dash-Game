import java.awt.*;
public class Obstacles {
    private int x,y;
    private int height, width;
    private int xSpeed, ySpeed;

    //Our obstacles are currently rectangles. X and Y represent where they are located on the game window, width and height are how big the obstacles are
    public Obstacles(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //testing

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

    //Pain function for obstacles, colors it black and fills it based on the parameters set for it.
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
    }

    //Shift function for obstacle. When the obstacle reaches the left side of the screen, shift/move the obstacle over to the right to make it look like the game is infinite
    public void shift(){
        if (this.x < 0){
            this.x = (int) (800+Math.random()*200);
            this.y = (int) (Math.random()*400);
        }
    }
}
