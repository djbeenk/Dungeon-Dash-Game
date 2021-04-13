import java.awt.*;
import java.util.LinkedList;

public class ObstacleList {
    LinkedList<Obstacles> obstacle = new LinkedList<Obstacles>();
    Obstacles tempObstacle;
    int gameOver;
    public ObstacleList(){
        gameOver = 0;

        for (int i = 0; i < 10; i ++){
            addObstacle(new Obstacles((int) (Math.random()*800), (int) (Math.random()*550), 20,40));
        }
    }

    public void paint(Graphics2D g2d){
        for(int i = 0; i < obstacle.size(); i++) {
            tempObstacle = obstacle.get(i);
            tempObstacle.paint(g2d);
        }

        //This part of the code doesn't work. When the obstacles reach the end of the screen, I want them to reappear
        if(tempObstacle.getX() == 0) {
            tempObstacle.setX((int) (Math.random()*800));
        }
    }

    public void update(){
        for (int i = 0; i < obstacle.size(); i++) {
            tempObstacle = obstacle.get(i);
            tempObstacle.update();
        }

        //This part of the code doesn't work. When the obstacles reach the end of the screen, I want them to reappear
        if(tempObstacle.getX() == 0) {
            tempObstacle.setX((int) (Math.random()*800));
        }
    }

    public void addObstacle(Obstacles obstacles){
        obstacle.add(obstacles);
    }

    //public void removeObstacle(Obstacles obstacles){
       // obstacle.remove(obstacles);
    //}
}
