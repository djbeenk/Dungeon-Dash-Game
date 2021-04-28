import java.awt.*;
import java.util.LinkedList;

public class ObstacleList {
    LinkedList<Obstacles> obstacle = new LinkedList<Obstacles>();
    Obstacles tempObstacle;
    int gameOver;
    public ObstacleList(){
        gameOver = 0;

        //Create 15 obstacles. Their locations are randomized but have a set width of 20 and a height of 40.
        for (int i = 0; i < 15; i ++){
            addObstacle(new Obstacles((int) (Math.random()*800), (int) (Math.random()*550), 20,40));
        }
    }

    //Method for painting each obstacle in the obstacleList. Uses a for list to iterate through each obstacle in the list. When an obstacle is called, it is stored in a temporary variable and painted before going onto the next obstacle in list.
    public void paint(Graphics2D g2d){
        for(int i = 0; i < obstacle.size(); i++) {
            tempObstacle = obstacle.get(i);
            tempObstacle.paint(g2d);
            tempObstacle.shift();
        }
    }

    //Method for updating each obstacle in the obstacleList.
    public void update(){
        for (int i = 0; i < obstacle.size(); i++) {
            tempObstacle = obstacle.get(i);
            tempObstacle.update();
        }
    }

    //Method for adding the obstacles to the list.
    public void addObstacle(Obstacles obstacles){
        obstacle.add(obstacles);
    }

}
