package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class Main extends JFrame {

    public Main() {
        super("Тестовое окно");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        final JLabel label = new JLabel("Выбранный файл");
        label.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton button = new JButton("ЖАТЬ");
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
                    File file = fileopen.getSelectedFile();
                    //label.setText(file.getName());
                    JFrame frame = new JFrame("Display Photo");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setSize(screenSize);
                    JLabel photoLabel = new JLabel();

                    try {
                        ImageIcon imageIcon = new ImageIcon(ImageIO.read(file));
                        photoLabel.setIcon(imageIcon);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error loading image: " + ex.getMessage());
                    }
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setSize(800, 600); // Начальный размер окна
                    setLocationRelativeTo(null); // Центрирование окна по центру экрана
                    photoLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    photoLabel.setVerticalAlignment(SwingConstants.CENTER);

                    getContentPane().setLayout(new BorderLayout());
                    getContentPane().add(button, BorderLayout.NORTH);
                    getContentPane().add(photoLabel, BorderLayout.CENTER);

                    frame.setLayout(new BorderLayout());
                    frame.add(photoLabel, BorderLayout.CENTER);
                    frame.setVisible(true);

                }
            }}
        );


        JButton button1 = new JButton("НЕ ЖАТЬ");
        button1.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(button1);
        panel.add(button);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(260, 220));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                new Main();
            }
        });
    }}