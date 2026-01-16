package software.ulpgc.imageviewer.application.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    public Main() throws HeadlessException, IOException {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        BufferedImage image = ImageIO.read(new File("images/godor1.jpg"));
        this.getContentPane().add(new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }



}
