import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class MainMenuPanel extends JPanel {
    private MainWindow frame;
    private JLabel start;
    private JButton startButton;
    public MainMenuPanel(MainWindow frame) {
        setFocusable(true);
        this.frame = frame;
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Endless Runner", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        title.setForeground(Color.DARK_GRAY);
        add(title, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,0,100,0));
        bottomPanel.setOpaque(false);
        start = new JLabel("Click start to begin.", SwingConstants.CENTER);
        start.setFont(new Font("Serif", Font.BOLD, 24));
        start.setForeground(Color.WHITE);
        start.setBackground(Color.DARK_GRAY);
        start.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new startListener());

        bottomPanel.add(start);
        bottomPanel.add(startButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private class startListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Begin.");
            frame.startGame();
        }
    }

}
