package padelmadridpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PantallaInicioSesion extends JFrame {

    private ImagePanel imagePanel;
    private JButton flechaButton; // Flecha como un objeto, no una imagen
    private BaseDatos baseDatos;  // Base de datos para validar usuarios

    public PantallaInicioSesion() {
        // Configuración de la ventana
        setTitle("Iniciar Sesión");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar la base de datos
        baseDatos = new BaseDatos();

        // Crear el panel de imagen con fondo
        imagePanel = new ImagePanel(new ImageIcon("src/imagenes/pantallainiciosesion.png").getImage());
        imagePanel.setLayout(null);

        // Botón de flecha utilizando la clase FlechaBack
        JButton flechaButton = new FlechaBack(e -> {
            new Main();  // Redirigir a la pantalla de Main
            dispose();
        });

        // Componentes de inicio de sesión
        JLabel userLabel = new JLabel("Correo Electrónico:");
        JTextField userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordText = new JPasswordField(20);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBorderPainted(false);

        // Acción para el botón de inicio de sesión
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = userText.getText().trim();
                String contrasena = new String(passwordText.getPassword()).trim();

                if (correo.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Obtener el nombre del usuario desde la base de datos
                String nombreUsuario = baseDatos.loginUsuario(correo, contrasena);

                if (nombreUsuario != null) { // Si el nombre no es null, el inicio es exitoso
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. Bienvenido, " + nombreUsuario + "!");
                    new PantallaPista(); // Pasar el nombre a la siguiente pantalla
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Añadir los componentes al panel
        imagePanel.add(flechaButton);
        imagePanel.add(userLabel);
        imagePanel.add(userText);
        imagePanel.add(passwordLabel);
        imagePanel.add(passwordText);
        imagePanel.add(loginButton);

        add(imagePanel, BorderLayout.CENTER);

        // Ajustar tamaño y posición de los componentes
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();

                flechaButton.setBounds(10, 10, 40, 40);
                userLabel.setBounds((width - 300) / 2, height / 3, 300, 30);
                userText.setBounds((width - 300) / 2, height / 3 + 40, 300, 30);
                passwordLabel.setBounds((width - 300) / 2, height / 3 + 90, 300, 30);
                passwordText.setBounds((width - 300) / 2, height / 3 + 130, 300, 30);
                loginButton.setBounds((width - 150) / 2, height / 3 + 180, 150, 40);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PantallaInicioSesion());
    }
}



