import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class MainWindow extends JFrame {
    public static int Height;
    public static int Width;
    private MainMenuPanel mainMenu;

    //The main window. Size and background color of the main menu are declared here
    public MainWindow() {
        super("Dungeon Dash");
        Height = 500;
        Width = 700;
        setSize(Width, Height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu = new MainMenuPanel(this);
        setContentPane(new BackgroundPanel("dungeon.png"));
        add(mainMenu);
        setVisible(true);
    }

    //The game menu. As soon as player clicks start, remove the main menu and load up the game
    public void startGame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
