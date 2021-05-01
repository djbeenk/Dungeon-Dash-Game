//I am reusing the below code from the Pong game that we went through in class earlier in the semester if that is okay. Credits go to Zach Amsler.

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class BackgroundPanel extends JPanel {
    private Image image;
    public BackgroundPanel(String filePath) {
        try {
            image = ImageIO.read(new File(filePath));
        } catch (Exception e) {
            System.err.println("Couldn't find image: " + filePath);
            setBackground(new Color(50,99,77));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

