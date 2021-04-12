import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public static int Height;
    public static int Width;

    private MainMenuPanel mainMenu;
    public MainWindow() {
        super("Endless Runner");
        Height = 500;
        Width = 700;
        setSize(Width, Height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu = new MainMenuPanel(this);
        mainMenu.setBackground(Color.PINK);
        add(mainMenu);
        setVisible(true);
    }

    public void startGame() {
        remove(mainMenu);
        mainMenu.setFocusable(false);
        JPanel gamePanel = new GamePanel();
        add(gamePanel);
        repaint();
        gamePanel.requestFocus();
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow();
    }
}
