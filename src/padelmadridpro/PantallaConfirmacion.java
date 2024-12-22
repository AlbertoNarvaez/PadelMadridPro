package padelmadridpro;

import javax.swing.*;
import java.awt.*;

public class PantallaConfirmacion extends JFrame {

    private String nombrePista;
    private String imagenRuta;
    private String dia;
    private String hora;
    private JButton confirmarButton;

    public PantallaConfirmacion(String nombrePista, String imagenRuta, String dia, String hora) {
        this.nombrePista = nombrePista;
        this.imagenRuta = imagenRuta;
        this.dia = dia;
        this.hora = hora;

        // Configuración de la ventana
        setTitle("Confirmación de Reserva");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel superior para la flecha y la pregunta
        JPanel panelSuperior = new JPanel(new BorderLayout());

        // Botón de flecha utilizando la clase FlechaBack
        JButton flechaButton = new FlechaBack(e -> {
            new PantallaReservas(nombrePista, imagenRuta); // Volver a la pantalla de reservas
            dispose();
        });
        panelSuperior.add(flechaButton, BorderLayout.WEST);

        JLabel preguntaLabel = new JLabel("¿Estás seguro de que quieres reservar?", SwingConstants.CENTER);
        preguntaLabel.setFont(new Font("Arial", Font.BOLD, 28));
        panelSuperior.add(preguntaLabel, BorderLayout.CENTER);

        add(panelSuperior, BorderLayout.NORTH);

        // Panel central para la imagen y los detalles
        JPanel panelCentral = new JPanel(new BorderLayout());

        // Imagen de la pista seleccionada
        ImageIcon imagenPista = new ImageIcon(getClass().getResource(imagenRuta));
        Image imagen = imagenPista.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(imagen));
        panelCentral.add(imagenLabel, BorderLayout.CENTER);

        // Panel inferior para los detalles de la reserva
        JPanel panelDetalles = new JPanel(new GridLayout(4, 1, 10, 10));
        panelDetalles.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Etiquetas para los detalles de la reserva
        JLabel pistaLabel = new JLabel("Pista seleccionada: " + nombrePista, SwingConstants.CENTER);
        pistaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel diaLabel = new JLabel("Día: " + dia, SwingConstants.CENTER);
        diaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel horaLabel = new JLabel("Hora: " + hora, SwingConstants.CENTER);
        horaLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Añadir las etiquetas al panel de detalles
        panelDetalles.add(pistaLabel);
        panelDetalles.add(diaLabel);
        panelDetalles.add(horaLabel);

        // Añadir el panel de detalles al panel central
        panelCentral.add(panelDetalles, BorderLayout.SOUTH);

        add(panelCentral, BorderLayout.CENTER);

        // Botón de confirmar reserva
        confirmarButton = new JButton("Confirmar Reserva");
        confirmarButton.setFont(new Font("Arial", Font.BOLD, 26));
        confirmarButton.setFocusPainted(false);
        confirmarButton.setContentAreaFilled(false);
        confirmarButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        confirmarButton.addActionListener(e -> {
            // Acción de confirmación de reserva
            JOptionPane.showMessageDialog(null, "Reserva confirmada\n" +
                            "Pista: " + nombrePista + "\nDía: " + dia + "\nHora: " + hora,
                    "Reserva Exitosa", JOptionPane.INFORMATION_MESSAGE);

            // Restablecer valores
            resetData();
        });

        // Añadir el botón a la parte inferior de la ventana
        add(confirmarButton, BorderLayout.SOUTH);

        // Hacer visible la ventana
        setVisible(true);
    }

    // Método para restablecer los valores seleccionados
    private void resetData() {
        this.nombrePista = null;
        this.imagenRuta = null;
        this.dia = null;
        this.hora = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new PantallaConfirmacion("Pista 4", "/imagenes/pista4.png", "Lunes", "18:00"));
    }
}
