package padelmadridpro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.regex.Pattern;

public class PantallaRegistro extends JFrame {

    private ImagePanel imagePanel;
    private BaseDatos baseDatos;

    public PantallaRegistro() {
        // Configuración de la ventana
        setTitle("Registrarse");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar la base de datos
        baseDatos = new BaseDatos();
        baseDatos.crearTablaUsuarios(); // Crear la tabla si no existe

        // Crear el panel de imagen con fondo
        imagePanel = new ImagePanel(new ImageIcon("src/imagenes/pantallainiciosesion.png").getImage());
        imagePanel.setLayout(null);

        // Botón de flecha utilizando la clase FlechaBack
        JButton flechaButton = new FlechaBack(e -> {
            new Main();  // Redirigir a la pantalla de Main
            dispose();
        });

        // Campos de texto para el registro
        JLabel nameLabel = new JLabel("Nombre y Apellidos:");
        JTextField nameText = new JTextField(20);
        JLabel emailLabel = new JLabel("Correo Electrónico:");
        JTextField emailText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Contraseña:");
        JPasswordField passwordText = new JPasswordField(20);
        JLabel confirmPasswordLabel = new JLabel("Confirmar Contraseña:");
        JPasswordField confirmPasswordText = new JPasswordField(20);
        JLabel phoneLabel = new JLabel("Teléfono:");
        JTextField phoneText = new JTextField(20);
        JLabel birthdateLabel = new JLabel("Fecha de Nacimiento (dd/mm/yyyy):");
        JTextField birthdateText = new JTextField(20);

        // Botón de registro
        JButton registerButton = new JButton("Registrarse");
        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBorderPainted(false);

        registerButton.addActionListener(e -> {
            String name = nameText.getText();
            String email = emailText.getText();
            String password = new String(passwordText.getPassword());
            String confirmPassword = new String(confirmPasswordText.getPassword());
            String phone = phoneText.getText();
            String birthdate = birthdateText.getText();

            // Validaciones
            if (name.chars().filter(ch -> ch == ' ').count() < 2) {
                JOptionPane.showMessageDialog(null, "El nombre y apellidos están incompletos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(null, "El correo electrónico debe contener un '@'.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(null, "El número de teléfono debe contener 9 dígitos.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", birthdate)) {
                JOptionPane.showMessageDialog(null, "La fecha de nacimiento debe tener el formato dd/mm/yyyy.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insertar usuario en la base de datos
            baseDatos.insert(new Usuario(name, email, password, phone, birthdate));
            JOptionPane.showMessageDialog(null, "Registro exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new PantallaInicioSesion();
        });

        // Añadir componentes al panel
        imagePanel.add(flechaButton);
        imagePanel.add(nameLabel);
        imagePanel.add(nameText);
        imagePanel.add(emailLabel);
        imagePanel.add(emailText);
        imagePanel.add(passwordLabel);
        imagePanel.add(passwordText);
        imagePanel.add(confirmPasswordLabel);
        imagePanel.add(confirmPasswordText);
        imagePanel.add(phoneLabel);
        imagePanel.add(phoneText);
        imagePanel.add(birthdateLabel);
        imagePanel.add(birthdateText);
        imagePanel.add(registerButton);

        add(imagePanel, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();
                int offsetY = 100;

                flechaButton.setBounds(10, 10, 40, 40);
                nameLabel.setBounds((width - 300) / 2, height / 4 + offsetY, 300, 30);
                nameText.setBounds((width - 300) / 2, height / 4 + 40 + offsetY, 300, 30);
                emailLabel.setBounds((width - 300) / 2, height / 4 + 80 + offsetY, 300, 30);
                emailText.setBounds((width - 300) / 2, height / 4 + 120 + offsetY, 300, 30);
                passwordLabel.setBounds((width - 300) / 2, height / 4 + 160 + offsetY, 300, 30);
                passwordText.setBounds((width - 300) / 2, height / 4 + 200 + offsetY, 300, 30);
                confirmPasswordLabel.setBounds((width - 300) / 2, height / 4 + 240 + offsetY, 300, 30);
                confirmPasswordText.setBounds((width - 300) / 2, height / 4 + 280 + offsetY, 300, 30);
                phoneLabel.setBounds((width - 300) / 2, height / 4 + 320 + offsetY, 300, 30);
                phoneText.setBounds((width - 300) / 2, height / 4 + 360 + offsetY, 300, 30);
                birthdateLabel.setBounds((width - 300) / 2, height / 4 + 400 + offsetY, 300, 30);
                birthdateText.setBounds((width - 300) / 2, height / 4 + 440 + offsetY, 300, 30);
                registerButton.setBounds((width - 150) / 2, height / 4 + 500 + offsetY, 150, 40);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PantallaRegistro::new);
    }
}






