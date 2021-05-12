import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.*;

public class ObstacleList {
    LinkedList<Obstacles> obstacle = new LinkedList<Obstacles>();
    LinkedList<Obstacles> bats = new LinkedList<Obstacles>();
    LinkedList<Obstacles> hearts = new LinkedList<>();
    Obstacles tempObstacle;
    Obstacles tempBat;
    Obstacles tempHeart;
    int gameOver;

    public ObstacleList() {
        gameOver = 0;

        //Create 15 obstacles/basic enemies. Their locations are randomized but have a set width of 60 and a height of 30.
        for (int i = 0; i < 5; i ++){
            addObstacle(new Obstacles((int) (400+Math.random()*800), (int) (Math.random()*550), 60,30));
        }

        //Create 5 bats. Their locations are randomized
        for (int i = 0; i < 5; i ++){
            addObstacleBat(new Obstacles((int) (300+Math.random()*800), (int) (Math.random()*550), 40,40));
        }

        //Create a heart
        for (int i = 0; i < 1; i++){
            addObstacleHeart(new Obstacles((int) (300+Math.random()*800), (int) (Math.random()*550), 40,40));
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
    public void update(Player passedPlayer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (passedPlayer.getGameOver() != true){
            for (int i = 0; i < obstacle.size(); i++) {
                tempObstacle = obstacle.get(i);
                tempObstacle.update();
                //HD checks collision upon move and will need a player to check against(added player pass requirement
                boolean collided = ObjectCollisionCheck(passedPlayer, tempObstacle);
                if(collided){
                    //If the player has collided, play sound effect.
                    File file2 = new File("enemyHit.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file2);
                    Clip clip2 = AudioSystem.getClip();
                    clip2.open(audioStream);
                    clip2.start();

                    //System.out.println(passedPlayer.getHealth());
                    passedPlayer.lowerLives(2);
                    removeObstacle(tempObstacle);
                    addObstacle(new Obstacles((int) (400+Math.random()*800), (int) (Math.random()*550), 60,30));
                }

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

    //Method for updating each bat in the list while game is not over
    public void updateBat(Player passedPlayer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (passedPlayer.getGameOver() != true){
            for (int i = 0; i < bats.size(); i++) {
                tempBat = bats.get(i);
                tempBat.updateBat();
                //HD checks collision upon move and will need a player to check against and relocate the collided object
                boolean collided = ObjectCollisionCheck(passedPlayer, tempBat);
                if (collided) {
                    //If player has collided, play sound effect
                    File file2 = new File("enemyHit.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file2);
                    Clip clip2 = AudioSystem.getClip();
                    clip2.open(audioStream);
                    clip2.start();

                    passedPlayer.lowerLives(1);
                    removeObstacleBat(tempBat);
                    addObstacleBat(new Obstacles((int) (300 + Math.random() * 800), (int) (Math.random() * 550), 40, 40));

                }
            }
        }
    }

    public void paintHeart(Graphics2D g2d){
        for (int i = 0; i < hearts.size(); i++){
            tempHeart = hearts.get(i);
            tempHeart.paintHeart(g2d);
            tempHeart.shift();
        }
    }

    public void updateHeart(Player passedPlayer) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (passedPlayer.getGameOver() != true){
            for (int i = 0; i < hearts.size(); i++) {
                tempHeart = hearts.get(i);
                tempHeart.updateHeart();
                //HD checks collision upon move and will need a player to check against and relocate the collided object
                boolean collided = ObjectCollisionCheck(passedPlayer, tempHeart);
                if (collided) {
                    //If player has collided, play sound effect
                    File file2 = new File("heartSound.wav");
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file2);
                    Clip clip2 = AudioSystem.getClip();
                    clip2.open(audioStream);
                    clip2.start();

                    passedPlayer.raiseLife(1);
                    removeObstacleHeart(tempHeart);
                    addObstacleHeart(new Obstacles((int) (300 + Math.random() * 800), (int) (Math.random() * 550), 40, 40));

                }
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

    public void addObstacleHeart(Obstacles heart) {
        hearts.add(heart);
    }

    public void removeObstacle(Obstacles obstacles){
        obstacle.remove(obstacles);
    }

    public void removeObstacleBat(Obstacles bat){
        bats.remove(bat);
    }

    public void removeObstacleHeart(Obstacles heart){
        hearts.remove(heart);
    }

    //HD, this function takes in a player and an obstacle and checks if the player touches the obstacle
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
                            return true;
                }
        }
        return false;
    }
}
