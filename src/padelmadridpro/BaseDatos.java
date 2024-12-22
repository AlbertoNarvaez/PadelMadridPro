package padelmadridpro;

import java.io.File;
import java.sql.*;

public class BaseDatos {

    // Cambia la ruta aquí: Base de datos en el directorio raíz del proyecto
    private static final String DATABASE_URL = "jdbc:sqlite:usuarios.db";

    // Método para conectar a la base de datos
    private Connection connect() {
        Connection conn = null;
        try {
            File dbFile = new File("usuarios.db");
            if (!dbFile.exists()) {
                System.out.println("Creando base de datos en: " + dbFile.getAbsolutePath());
            }
            conn = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conn;
    }

    // Crear la tabla si no existe
    public void crearTablaUsuarios() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " nombre TEXT NOT NULL,\n"
                + " correo TEXT NOT NULL,\n"
                + " contrasena TEXT NOT NULL,\n"
                + " telefono TEXT NOT NULL,\n"
                + " fechaNacimiento TEXT NOT NULL\n"
                + ");";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla 'usuarios' creada o ya existe.");
        } catch (SQLException e) {
            System.out.println("Error al crear la tabla: " + e.getMessage());
        }
    }

    // Insertar un usuario
    public void insert(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, correo, contrasena, telefono, fechaNacimiento) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getCorreo());
            pstmt.setString(3, usuario.getContrasena());
            pstmt.setString(4, usuario.getTelefono());
            pstmt.setString(5, usuario.getFechaNacimiento());
            pstmt.executeUpdate();
            System.out.println("Usuario registrado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

    // Validar inicio de sesión y devolver el nombre
    public String loginUsuario(String correo, String contrasena) {
        String nombreCompleto = null;
        String query = "SELECT nombre FROM usuarios WHERE correo = ? AND contrasena = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasena);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nombreCompleto = rs.getString("nombre"); // Obtener el nombre del usuario
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + nombreCompleto);
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }

        return nombreCompleto; // Retorna el nombre completo si existe, null si no
    }

    public static void main(String[] args) {
        BaseDatos db = new BaseDatos();

        // Crear la tabla usuarios
        db.crearTablaUsuarios();

        // Insertar un usuario de prueba
        Usuario usuario = new Usuario("Alberto", "alberto@example.com", "12345", "123456789", "2001-09-07");
        db.insert(usuario);

        // Probar inicio de sesión
        String nombreUsuario = db.loginUsuario("alberto@example.com", "12345");
        if (nombreUsuario != null) {
            System.out.println("El nombre del usuario es: " + nombreUsuario);
        } else {
            System.out.println("Inicio de sesión fallido.");
        }
    }

    public void close() {
    }
}
