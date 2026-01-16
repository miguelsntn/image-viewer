package software.ulpgc.imageviewer.application.gui;

import software.ulpgc.imageviewer.architecture.Image;
import software.ulpgc.imageviewer.architecture.ImageDisplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image currentImage;
    private BufferedImage bitmap;
    @Override
    public void show(Image image) {
        this.currentImage = image;
        try {
            this.bitmap = ImageIO.read(new ByteArrayInputStream(image.bitmap()));
            this.repaint();
            Window win = SwingUtilities.getWindowAncestor(this);
            if (win instanceof JFrame) {
                ((JFrame) win).setTitle("Image Viewer -" + image.id());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Image image() {
        return currentImage;
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        if (bitmap != null) {
            int targetWidth = getWidth();
            int targetHeight = getHeight();
            double imgAspect = (double) bitmap.getWidth() / bitmap.getHeight();
            double panelAspect = (double) targetWidth / targetHeight;
            int drawWidth, drawHeight = 0;
            if (imgAspect > panelAspect) {
                drawWidth = targetWidth;
                drawHeight = (int)(targetWidth / imgAspect);
            } else {
                drawWidth = targetHeight;
                drawWidth = (int) (targetHeight * imgAspect);
            }
            int x = (targetWidth - drawWidth) / 2;
            int y = (targetHeight - drawHeight) /2;
            g.drawImage(bitmap, x, y, drawWidth, drawHeight, null);
        }
    }
}
