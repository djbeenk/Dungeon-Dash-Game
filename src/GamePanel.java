import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;


public class GamePanel extends JPanel {
    private Player player;
    private int y;
    ObstacleList o_list;
    ObstacleList bat_list;
    ObstacleList heart_list;
    Timer timer;
    private int highScore;
    Clip clip = null;

    public GamePanel() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        setFocusable(true);
        //Set the size of the game window
        setSize(700, 500);
        //Initialize the dimensions of the player character (a sphere at the moment)
        player = new Player(100, 300, 10);
        //Linked list called obstacleList which stores all of our obstacles
        o_list = new ObstacleList();
        bat_list = new ObstacleList();
        heart_list = new ObstacleList();
        setOpaque(false);
        timer = new Timer(100, new TimerListener());
        timer.start();
        //KeyListener for up and down arrow
        this.addKeyListener(new jumpListen());

//Music for the game. Opens the file, starts it, and loops continuously.
        File file = new File("music.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    void stopTimer() {
        this.timer.stop();
    }

    //Method for painting the player and the obstacles in obstacle list
    @Override
    public void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);
        player.paint(g2d);
        o_list.paint((Graphics2D) g2d);
        bat_list.paintBat((Graphics2D) g2d);
        heart_list.paintHeart((Graphics2D) g2d);
    }

    private void update() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        o_list.updateBasicEnemy(player);
        bat_list.updateBat(player);
        heart_list.updateHeart(player);
    }

    private class TimerListener implements ActionListener {
        public int x = 0;

        //Method for the player character. We will repaint and update everytime we move up or down and track the score while game is not over.
        @Override
        public void actionPerformed(ActionEvent e) {
            //if the game is still going, continue updating. If it's game over, stop updating
            if (!player.getGameOver()) {
                x++;
                System.out.println(x);
                try {
                    update();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                repaint();
                if (x % 10 == 0) {
                    player.addScore(10);
                }
                //if the game ends
            } else {
                stopTimer();
                System.out.println("Game End");
                //Stop the music once we reach game over
                clip.stop();

                //Play a game over sound effect if you lose. There were a few errors so I let IntelliJ fix them with all of these catch exceptions. The code works though.
                File file2 = new File("gameover.wav");
                AudioInputStream audioStream = null;
                try {
                    audioStream = AudioSystem.getAudioInputStream(file2);
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Clip clip2 = null;
                try {
                    clip2 = AudioSystem.getClip();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                }
                try {
                    clip2.open(audioStream);
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                clip2.start();


                System.out.println(player.getScore());
                if (player.getScore() > player.getHighScore()) {
                    player.updateHighScore();
                }

            }
        }
    }
    //Method to listen for anytime the player presses the up or down arrow. Calls the player jump and jumpDown methods located in player.java
    private class jumpListen extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == e.VK_UP) {
                player.jump();
            }

            else if (e.getKeyCode() == e.VK_DOWN) {
                player.jumpDown();
            }
        }
    }
}
