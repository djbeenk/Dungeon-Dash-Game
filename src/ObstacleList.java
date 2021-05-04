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
    public void update(Player passedPlayer){
        for (int i = 0; i < obstacle.size(); i++) {
            tempObstacle = obstacle.get(i);
            tempObstacle.update();
            //HD checks collision upon move and will need a player to check against(added player pass requirement
            boolean collided = ObjectCollisionCheck(passedPlayer, tempObstacle);
            if(collided){
                System.out.println("player has collided");
                //needs a way to remove the obstacle so it does not collide again. this method did not work
                removeObstacle(tempObstacle);
                addObstacle(new Obstacles((int) (400+Math.random()*800), (int) (Math.random()*550), 60,30));

            }
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
    public void updateBat(Player passedPlayer) {
        for (int i = 0; i < bats.size(); i++) {
            tempBat = bats.get(i);
            tempBat.updateBat();
            //HD checks collision upon move and will need a player to check against(added player pass requirement
            boolean collided = ObjectCollisionCheck(passedPlayer, tempBat);
            if (collided) {
                System.out.println("player has collided");
                //needs a way to remove the obstacle so it does not collide again. this method did not work
                removeObstacleBat(tempBat);
                addObstacleBat(new Obstacles((int) (300+Math.random()*800), (int) (Math.random()*550), 40,40));

            }
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

    public void removeObstacle(Obstacles obstacles){
        obstacle.remove(obstacles);
    }

    public void removeObstacleBat(Obstacles bat){
        bats.remove(bat);
    }

    //HD this function takes in a player and an obstacle and checks if the player touches the obstacle
    public boolean ObjectCollisionCheck (Player tempPlayer, Obstacles obstacleCheck){
        Obstacles myObstacle = obstacleCheck;

        int ObstacleHeight = myObstacle.getY() + myObstacle.getHeight();

        int PlayerHead = tempPlayer.getY();
        int playerFeet = tempPlayer.getY() + tempPlayer.getHeight();
        int PlayerLeftMost = tempPlayer.getX();

        int PlayerRightMost = tempPlayer.getX() + tempPlayer.getPlayerWidth();
        int ObstacleRightMost = myObstacle.getX() + myObstacle.getWidth();

        if (PlayerHead + 10 <= ObstacleHeight && playerFeet >= myObstacle.getY()){
                if(PlayerLeftMost <= ObstacleRightMost && PlayerRightMost >= myObstacle.getX()) {
                            //System.out.println("Collision!" + collisionCount);
                            return true;
                }
            //}
        }
        return false;
        //this function will need to return a boolean to trigger lives
    }


}
