import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ObstacleList {
    LinkedList<Obstacles> obstacle = new LinkedList<Obstacles>();
    LinkedList<Obstacles> bats = new LinkedList<Obstacles>();
    Obstacles tempObstacle;
    Obstacles tempBat;
    int gameOver;

    public ObstacleList(){
        gameOver = 0;

        //Create 15 obstacles/basic enemies. Their locations are randomized but have a set width of 60 and a height of 30.
        for (int i = 0; i < 15; i ++){
            addObstacle(new Obstacles((int) (400+Math.random()*800), (int) (Math.random()*550), 60,30));
        }

        //Create 5 bats. Their locations are randomized
        for (int i = 0; i < 5; i ++){
            addObstacleBat(new Obstacles((int) (300+Math.random()*800), (int) (Math.random()*550), 40,40));
        }
    }

    //Method for painting each basic enemy in the obstacleList. Uses a for list to iterate through each obstacle in the list. When an obstacle is called, it is stored in a temporary variable and painted before going onto the next obstacle in list.
    public void paint(Graphics2D g2d){
        for(int i = 0; i < obstacle.size(); i++) {
            tempObstacle = obstacle.get(i);
            tempObstacle.paint(g2d);
            tempObstacle.shift();
        }
    }

    //Method for updating each obstacle/basic enemy in the obstacleList.
    public void update(){
        for (int i = 0; i < obstacle.size(); i++) {
            tempObstacle = obstacle.get(i);
            tempObstacle.update();
        }
    }

    //Method for painting each bat in batList
    public void paintBat(Graphics2D g2d){
        for (int i = 0; i < bats.size(); i++){
            tempBat = bats.get(i);
            tempBat.paintBat(g2d);
            tempBat.shift();
        }
    }

    //Method for updating each bat in the list
    public void updateBat(){
        for (int i = 0; i < bats.size(); i++) {
            tempBat = bats.get(i);
            tempBat.updateBat();
        }
    }

    //Method for adding the obstacles/basic enemy to the list.
    public void addObstacle(Obstacles obstacles){
        obstacle.add(obstacles);
    }

    //Method for adding bat to the list
    public void addObstacleBat(Obstacles bat){
        bats.add(bat);
    }

}
