package software.ulpgc.imageviewer.application.gui;

import software.ulpgc.imageviewer.application.FileImageStore;
import software.ulpgc.imageviewer.architecture.Command;
import software.ulpgc.imageviewer.architecture.Image;
import software.ulpgc.imageviewer.architecture.ImageProvider;
import software.ulpgc.imageviewer.architecture.NextCommand;
import software.ulpgc.imageviewer.architecture.PrevCommand;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main extends JFrame {
    public static final String root = "images";

    private final SwingImageDisplay imageDisplay;

    public Main() {
        this.setTitle("Image Viewer V3");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.imageDisplay = new SwingImageDisplay();
        this.add(imageDisplay, BorderLayout.CENTER);

        this.add(createToolbar(), BorderLayout.SOUTH);
    }

    private Component createToolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel.add(createButton("<", new PrevCommand(imageDisplay)));
        panel.add(createButton(">", new NextCommand(imageDisplay)));

        return panel;
    }

    private JButton createButton(String label, Command command) {
        JButton button = new JButton(label);
        button.addActionListener(e -> command.execute());
        return button;
    }

    public static void main(String[] args) {
        Main frame = new Main();
        File folder = new File(root);

        if (!folder.exists()) {
            JOptionPane.showMessageDialog(null, "No encuentro la carpeta images.");
            return;
        }

        Image image = ImageProvider.with(new FileImageStore(folder).images())
                .first(Main::loader);

        if (image == null) {
            JOptionPane.showMessageDialog(null, "La carpeta 'images' está vacía o no tiene archivos legibles.");
            return;
        }

        frame.imageDisplay.show(image);
        frame.setVisible(true);
    }

    private static byte[] loader(String id) {
        try (InputStream is = new FileInputStream(new File(root, id))) {
            return is.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar la imagen: " + id, e);
        }
    }
}