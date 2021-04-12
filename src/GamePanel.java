import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private Player player;
    private Obstacles obstacle, obstacle2, obstacle3;
    private int y;
    Controller c;


    public GamePanel() {
        setFocusable(true);
        setSize(700, 500);
        player = new Player(100, 300, 10);
        c = new Controller();
        //obstacle = new Obstacles((int) (Math.random()*800), (int) (Math.random()*350), 20,40);
        //obstacle2 = new Obstacles((int) (Math.random()*900), (int) (Math.random()*400), 20,40);
        //obstacle = new Obstacles(700, 273, 20, 40);
        //obstacle2 = new Obstacles(500, 100, 20, 40);
        //obstacle3 = new Obstacles(400,350,20,40);

        setOpaque(false);
        Timer timer = new Timer(100, new TimerListener());
        timer.start();
        this.addKeyListener(new jumpListen());
    }

    private static int randomizer(int min, int max){
        Random random = new Random();
        int number = random.nextInt(max-min);
        number = number + min;
        return number;
    }

    @Override
    public void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);
        player.paint(g2d);
        c.paint((Graphics2D) g2d);

        //obstacle.paint(g);
        //obstacle2.paint(g);
        //obstacle.paint(g);
        //obstacle2.paint(g);
        //obstacle3.paint(g);
    }

    private void update() {

        //obstacle.update();
        //obstacle2.update();
        //obstacle.update();
        //obstacle2.update();
        //obstacle3.update();

        c.update();
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


