package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PaintPanel extends JPanel implements MouseMotionListener {

    private int mouseX = -1;
    private int mouseY = -1;
    public PaintPanel() {
        addMouseMotionListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (mouseX != -1 && mouseY != -1) {
            g.setColor(Color.RED);
            g.fillRect(mouseX, mouseY,1, 1);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Red Stripe Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            PaintPanel redStripePanel = new PaintPanel();
            frame.add(redStripePanel);

            frame.setVisible(true);
        });
    }

}
