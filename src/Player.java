import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class Player {
    private int x, y;
    //radius is not being used currently
    private int radius;
    private int playerWidth = 50;
    private int playerHeight = 50;
    Image player;
    private int Score = 0;
    private int Health = 5;
    private boolean gameOver = false;

    private int highScore;
    File highScoreFile;

    //Player character is currently a sphere, x and y are locations while radius is for the shape
    public Player(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        player = new ImageIcon("player.gif").getImage();

        highScoreFile = new File("highscore.txt");
        try {
            Scanner highScoreScanner = new Scanner(highScoreFile);
            highScore = highScoreScanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //returns the current high score
    public int getHighScore() {
        return this.highScore;
    }

    //updates the high score if the score was higher than the previous high score saved in the highscore.txt file
    public void updateHighScore() {
        try {
            FileWriter fw = new FileWriter(highScoreFile);
            PrintWriter pw = new PrintWriter(fw);
            System.out.println(this.Score);
            pw.println(this.Score);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //returns the current score
    public int getScore() {
        return this.Score;
    }

    //Not using below functions currently, may use in future
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
    //Not using above functions currently, may use in future

    //Jump function for player character. Every time player clicks up, moves player up 15 pixels
    public void jump() {
        //HD added code to limit jump to top of screen and a freeze upon game over
        if (this.y >=15 && (gameOver==false)){
            this.y -= 15;
        }
    }

    //Jump down function. Everytime player clicks down arrow, moves player down 15 pixels
    public void jumpDown() {
        //HD added code to limit jump ot bottom of first screen and a freeze upon game over
        if(this.y <= 400 && (gameOver==false)){
            this.y += 15;
        }
    }

    public int getHeight() {
        return playerHeight;
    }

    public int getPlayerWidth(){
        return playerWidth;
    }

    //Paint function.
    public void paint(Graphics g) {
        //The below functions will paint the score and health on to the screen while game is not over
        g.drawString("Score: " + intAsText(Score), 20, 150);
        g.drawString("Health: " + intAsText(Health), 20, 170);
        g.drawString("High Score: " + intAsText(highScore), 20, 190);

        if (gameOver){
            g.drawString("GAME OVER", 100, 200);
        }
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(player, x, y, playerWidth, playerHeight,null);
    }
    public void addScore(int points){
        Score = Score + points;
    }
    //lowers the players health if they take damage
    public void lowerLives(int damage){
        if (Health > 0) {
            Health= Health - damage;
            if (Health <= 0){
                gameOver = true;
            }
        }
    }

    public void raiseLife(int damage){
        if (Health > 0){
            Health= Health + damage;
            if (Health <=0){
                gameOver = true;
            }
        }
    }
    // it converts an int onto a text, used for score
    public String intAsText (int currentScore){
        String myScore = Integer.toString(currentScore);
        return myScore;
    }
    //passes gameOver status
    public boolean getGameOver(){
        return gameOver;
    }
    //method for changing the game over status
    public void setGameOver(boolean isOver) {
        this.gameOver = isOver;
    }
}
