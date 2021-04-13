import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private Player player;
    private int y;
    ObstacleList o_list;

    public GamePanel() {
        setFocusable(true);
        setSize(700, 500);
        player = new Player(100, 300, 10);
        o_list = new ObstacleList();

        setOpaque(false);
        Timer timer = new Timer(100, new TimerListener());
        timer.start();
        this.addKeyListener(new jumpListen());
    }

    @Override
    public void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);
        player.paint(g2d);
        o_list.paint((Graphics2D) g2d);
    }

    private void update() {
        o_list.update();
    }

    public void JumpKeyListener(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_SPACE) {
            player.jump();
            player.jumpDown();
        }
    }

    private class TimerListener implements ActionListener {
        public int x = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            x++;
            System.out.println(x);
            update();
            repaint();
        }
    }

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


