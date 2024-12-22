package padelmadridpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaReservas extends JFrame {
    private String selectedDay = null;  // Día seleccionado
    private String selectedHour = null; // Hora seleccionada

    public PantallaReservas(String nombrePista, String imagenRuta) {

        // Configuración de la ventana
        setTitle("Reservar " + nombrePista);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el botón de flecha utilizando la clase FlechaBack
        JButton flechaButton = new FlechaBack(e -> {
            new PantallaPista();  // Redirigir a la pantalla de Pista
            dispose();
        });

        // Panel superior para la flecha y el título
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(flechaButton, BorderLayout.WEST); // Añadir la flecha a la izquierda

        // Etiqueta con el mensaje de selección
        JLabel seleccionLabel = new JLabel("Has seleccionado la " + nombrePista, SwingConstants.CENTER);
        seleccionLabel.setFont(new Font("Arial", Font.BOLD, 30));
        panelSuperior.add(seleccionLabel, BorderLayout.CENTER); // Añadir el texto al centro

        add(panelSuperior, BorderLayout.NORTH); // Añadir el panel superior al borde norte

        // Panel de la imagen de la pista
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Imagen de la pista seleccionada
        ImageIcon imagenPista = new ImageIcon(getClass().getResource(imagenRuta));
        Image imagen = imagenPista.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(imagen));
        panelImagen.add(imagenLabel);
        add(panelImagen, BorderLayout.CENTER);

        // Panel de mensaje
        JLabel mensajeLabel = new JLabel("¡Escoge día/hora y juegue ya!", SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Panel con el horario
        JPanel panelHorario = new JPanel();
        panelHorario.setLayout(new GridLayout(6, 5, 5, 5)); // 6 filas, 5 columnas con espacio

        // Días de la semana
        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes"};
        for (String dia : diasSemana) {
            JLabel diaLabel = new JLabel(dia, SwingConstants.CENTER);
            diaLabel.setFont(new Font("Arial", Font.BOLD, 18));
            diaLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            diaLabel.setOpaque(true);

            // Listener para seleccionar día
            diaLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Cambiar el color del día seleccionado
                    selectedDay = dia;
                    Component[] components = panelHorario.getComponents();
                    for (Component component : components) {
                        if (component instanceof JLabel) {
                            component.setBackground(null); // Restablecer color original
                        }
                    }
                    diaLabel.setBackground(Color.DARK_GRAY); // Fijar el color del día seleccionado
                    diaLabel.setForeground(Color.WHITE); // Cambiar el texto a blanco
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!dia.equals(selectedDay)) {
                        diaLabel.setBackground(Color.DARK_GRAY); // Cambiar a verde oscuro al pasar el ratón
                        diaLabel.setForeground(Color.WHITE); // Cambiar texto a blanco
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!dia.equals(selectedDay)) {
                        diaLabel.setBackground(null); // Restaurar el color original si no está seleccionado
                        diaLabel.setForeground(Color.BLACK); // Restaurar texto a negro
                    }
                }
            });

            panelHorario.add(diaLabel);
        }

        // Horas para cada día
        String[][] horasPorDia = {
                {"10:00", "10:30", "11:00", "11:30", "12:00"},
                {"12:30", "13:00", "13:30", "14:00", "14:30"},
                {"15:00", "15:30", "16:00", "16:30", "17:00"},
                {"17:30", "18:00", "18:30", "19:00", "19:30"},
                {"20:00", "20:30", "21:00", "21:30", "22:00"},
        };

        for (int i = 0; i < horasPorDia.length; i++) {
            for (int j = 0; j < horasPorDia[i].length; j++) {
                JLabel horaLabel = new JLabel(horasPorDia[i][j], SwingConstants.CENTER);
                horaLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                horaLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                horaLabel.setOpaque(true);

                horaLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (selectedDay == null) {
                            JOptionPane.showMessageDialog(null, "Primero debes seleccionar un día.");
                        } else {
                            selectedHour = horaLabel.getText();
                            int confirm = JOptionPane.showConfirmDialog(null,
                                    "Has seleccionado: " + selectedDay + " a las " + selectedHour + ". ¿Deseas continuar?",
                                    "Confirmar selección", JOptionPane.YES_NO_OPTION);
                            if (confirm == JOptionPane.YES_OPTION) {
                                // Llevar a la pantalla de confirmación
                                new PantallaConfirmacion(nombrePista, imagenRuta, selectedDay, selectedHour);
                                dispose(); // Cerrar la pantalla actual
                            }
                        }
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        horaLabel.setBackground(Color.GREEN); // Color verde al pasar por encima
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        horaLabel.setBackground(null); // Restaurar color original
                    }
                });

                panelHorario.add(horaLabel);
            }
        }

        // Añadir al centro
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(mensajeLabel, BorderLayout.NORTH);
        panelCentro.add(panelHorario, BorderLayout.CENTER);

        add(panelCentro, BorderLayout.SOUTH);

        // Hacer visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new PantallaReservas("Pista 4", "/imagenes/pista4.png"));
    }
}
