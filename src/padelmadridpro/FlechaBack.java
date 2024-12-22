package padelmadridpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FlechaBack extends JButton {

    public FlechaBack(ActionListener actionListener) {
        // Configuración del botón
        setPreferredSize(new Dimension(40, 40));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        // Dibujar la flecha personalizada
        addActionListener(actionListener);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.BLACK);

        // Dibujar la flecha (un triángulo y una línea)
        int[] xPoints = {15, 5, 15};
        int[] yPoints = {5, 15, 25};
        g2d.fillPolygon(xPoints, yPoints, 3); // Flecha triangular
        g2d.drawLine(15, 15, 30, 15);         // Línea horizontal
        g2d.dispose();
    }
}
