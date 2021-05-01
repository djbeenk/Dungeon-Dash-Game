import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;

public class GamePanel extends JPanel {
    private Player player;
    private int y;
    ObstacleList o_list;
    ObstacleList bat_list;

    public GamePanel() {
        setFocusable(true);
        //Set the size of the game window
        setSize(700, 500);
        //Initialize the dimensions of the player character (a sphere at the moment)
        player = new Player(100, 300, 10);
        //Linked list called obstacleList which stores all of our obstacles
        o_list = new ObstacleList();
        bat_list = new ObstacleList();
        setOpaque(false);
        Timer timer = new Timer(100, new TimerListener());
        timer.start();
        //KeyListener for up and down arrow
        this.addKeyListener(new jumpListen());
    }

    //Method for painting the player and the obstacles in obstacle list
    @Override
    public void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);
        player.paint(g2d);
        o_list.paint((Graphics2D) g2d);
        bat_list.paintBat((Graphics2D) g2d);
    }

    private void update() {
        o_list.update(player);
        bat_list.updateBat(player);
    }

    private class TimerListener implements ActionListener {
        public int x = 0;

        //Method for the player character. We will repaint and update everytime we move up or down.
        @Override
        public void actionPerformed(ActionEvent e) {
            x++;
            //System.out.println(x);
            update();
            repaint();
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
    //HD player collision check requires a list of objects
}


