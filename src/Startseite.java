import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Startseite extends JComponent {
    private static final Color backgroundColor = new Color(183, 168, 111);
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.setFont(new Font("Default", Font.PLAIN, 20));

        g2D.setColor(backgroundColor);
        g2D.fillRect(0, 0, 800, 600);
    }
}