import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainMenuPanel extends JPanel {
    private MainWindow frame;
    private JLabel start;
    private JButton startButton;

    public MainMenuPanel(MainWindow frame) {
        setFocusable(true);
        this.frame = frame;
        setLayout(new BorderLayout());
//Title of the game, font size, and the color of the title are initialized below
        JLabel title = new JLabel("Dungeon Dash", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        title.setForeground(Color.black);
        add(title, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));
        bottomPanel.setOpaque(false);
        start = new JLabel("Click start to begin.", SwingConstants.CENTER);
        start.setFont(new Font("Serif", Font.BOLD, 24));
        start.setForeground(Color.black);
        start.setBackground(Color.DARK_GRAY);
        start.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new startListener());

        //Adds the "click start to begin" label and the button to the panel
        bottomPanel.add(start);
        bottomPanel.add(startButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    //Listener to check when the player clicks the start game button
    private class startListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Begin.");
            try {
                frame.startGame();
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }
        }
    }
}
