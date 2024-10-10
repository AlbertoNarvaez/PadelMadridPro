package org.example.padelmadridpro;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaInicioSesion {

    // Método para mostrar la pantalla de inicio de sesión
    public void mostrarPantallaInicioSesion(Stage primaryStage) {
        // Cargamos la imagen de fondo, ajustando la transparencia
        Image imagenFondo = new Image("C:\\Users\\alber\\OneDrive - Universidad Antonio De Nebrija\\Nebrija\\Técnicas de Programación Avanzada\\APP PADEL\\PadelMadridPRO\\imagen\\pantallainiciosesion.png");
        ImageView imagenView = new ImageView(imagenFondo);
        imagenView.setFitWidth(600);
        imagenView.setFitHeight(600);
        imagenView.setOpacity(0.7); // Ajusta la transparencia si es necesario

        // Flecha para volver atrás
        ImageView flechaAtras = new ImageView(new Image("C:\\Users\\alber\\OneDrive - Universidad Antonio De Nebrija\\Nebrija\\Técnicas de Programación Avanzada\\APP PADEL\\PadelMadridPRO\\imagen\\flechanegra.png"));
        flechaAtras.setFitHeight(30);
        flechaAtras.setFitWidth(30);
        Button botonAtras = new Button("", flechaAtras);
        botonAtras.setStyle("-fx-background-color: transparent;");
        botonAtras.setOnAction(e -> {
            PantallaInicial pantallaInicial = new PantallaInicial();
            pantallaInicial.start(primaryStage); // Volver a la pantalla inicial
        });

        // Layout para la flecha de atrás
        HBox layoutFlecha = new HBox(botonAtras);
        layoutFlecha.setAlignment(Pos.TOP_LEFT);
        layoutFlecha.setPadding(new Insets(10));

        // Etiqueta y campo de texto para "Nombre de Usuario"
        Label labelUsuario = new Label("Nombre de Usuario:");
        TextField campoUsuario = new TextField();
        campoUsuario.setPromptText("Introduce tu nombre de usuario");
        campoUsuario.setMaxWidth(250);

        // Etiqueta y campo de texto para "Contraseña"
        Label labelContrasena = new Label("Contraseña:");
        PasswordField campoContrasena = new PasswordField();
        campoContrasena.setPromptText("Introduce tu contraseña");
        campoContrasena.setMaxWidth(250);

        // Botón de "Iniciar Sesión"
        Button botonIniciarSesion = new Button("Iniciar Sesión");
        botonIniciarSesion.setStyle("-fx-background-color: black; " +  // Cambiamos el color a negro
                "-fx-text-fill: white; " +
                "-fx-font-size: 18px; " +
                "-fx-font-family: 'Verdana'; " +
                "-fx-background-radius: 30; " +
                "-fx-padding: 10 20 10 20;");

        // Acción del botón "Iniciar Sesión"
        botonIniciarSesion.setOnAction(e -> {
            String usuario = campoUsuario.getText();
            String contrasena = campoContrasena.getText();

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                System.out.println("Por favor, rellena ambos campos.");
            } else {
                if (validarCredenciales(usuario, contrasena)) {
                    System.out.println("Inicio de sesión correcto.");
                } else {
                    System.out.println("Usuario o contraseña incorrectos.");
                }
            }
        });

        // Layout para los elementos
        VBox layoutCampos = new VBox(15);
        layoutCampos.getChildren().addAll(labelUsuario, campoUsuario, labelContrasena, campoContrasena, botonIniciarSesion);
        layoutCampos.setAlignment(Pos.CENTER);

        // Usamos StackPane para superponer la imagen de fondo y los campos de inicio de sesión
        StackPane root = new StackPane();
        root.getChildren().addAll(imagenView, layoutCampos, layoutFlecha);

        // Crear la escena y establecerla en la ventana principal
        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Iniciar Sesión");
        primaryStage.show();
    }

    // Método de ejemplo para validar las credenciales
    private boolean validarCredenciales(String usuario, String contrasena) {
        return usuario.equals("admin") && contrasena.equals("admin");
    }
}

