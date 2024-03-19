package org.example;

import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    private String userName;
    private JButton startButton;
    File file;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main guiTest = new Main();
            guiTest.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        setTitle("Привет");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        startButton = new JButton("Ввод");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = JOptionPane.showInputDialog("Введите имя");
                create2NewButtons();
            }
        });
        setPreferredSize(new Dimension(260, 220));
        add(startButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void create2NewButtons() {
        setTitle(userName +", выберите как загрузить фото");
        remove(startButton);

        JButton button = new JButton("Выбор из файлов");
        button.setAlignmentX(CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                JFileChooser fileopen = new JFileChooser();
                fileopen.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        if (file.isDirectory()) {
                            return true;
                        }
                        String name = file.getName().toLowerCase();
                        return (name.endsWith(".png") || name.endsWith(".jpeg")) && !name.endsWith(".app");
                    }

                    @Override
                    public String getDescription() {
                        return "PNG и JPEG файлы (*.png, *.jpeg)";
                    }
                });

                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    file = fileopen.getSelectedFile();
                    JFrame frame = new JFrame("Подтвердите фото");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setSize(screenSize);
                    JLabel photoLabel = printPhoto(file);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setSize(300, 400); // Начальный размер окна


                    JButton acceptButton = new JButton("Дальше");
                    accepting(acceptButton, file, frame);
                    frame.add(acceptButton, BorderLayout.LINE_END);

                    frame.add(photoLabel, BorderLayout.CENTER);
                    frame.setVisible(true);
                }

            }}
        );
        JButton button2 = new JButton("Снять фото");

        add(button);
        add(button2);

        revalidate();
        repaint();
    }
    public JLabel printPhoto(File file){

        JLabel photoLabel = new JLabel();

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(file));
            photoLabel.setIcon(imageIcon);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading image: " + ex.getMessage());
        }
        return photoLabel;
    }
    public void accepting(JButton button, File file1, JFrame frame){

    }
}