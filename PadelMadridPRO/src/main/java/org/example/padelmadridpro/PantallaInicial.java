package org.example.padelmadridpro;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PantallaInicial extends Application {

    private String NombreApp = "PADEL MADRID PRO";

    @Override
    public void start(Stage primaryStage) {
        // Establecemos el título de la ventana
        primaryStage.setTitle(NombreApp);

        // Cargamos la imagen de fondo y ajustamos la transparencia
        Image imagenFondo = new Image("C:\\Users\\Alberto\\OneDrive - Universidad Antonio De Nebrija\\Nebrija\\Técnicas de Programación Avanzada\\APP PADEL\\PadelMadridPRO\\imagen\\pantallainicial.png"); // Cambia la ruta a la ubicación de tu imagen
        ImageView imagenView = new ImageView(imagenFondo);
        imagenView.setFitWidth(600);
        imagenView.setFitHeight(600);
        imagenView.setOpacity(0.8); // Ajusta la transparencia del fondo (0.0 a 1.0)

        // Creamos los botones "Iniciar Sesión" y "Registrarse"
        Button botonIniciarSesion = new Button("Iniciar Sesión");
        Button botonRegistro = new Button("Registrarse");

        // Aplicamos estilos a los botones
        String estiloBotones = "-fx-background-color: #FF5722; " + // Color de fondo naranja
                "-fx-text-fill: white; " +          // Color del texto blanco
                "-fx-font-size: 20px; " +           // Tamaño de la fuente
                "-fx-font-family: 'Verdana'; " +    // Tipo de fuente
                "-fx-background-radius: 30; " +     // Bordes redondeados
                "-fx-padding: 10 20 10 20;";        // Relleno interno del botón
        botonIniciarSesion.setStyle(estiloBotones);
        botonRegistro.setStyle(estiloBotones);

        // Añadimos efectos al pasar el mouse sobre los botones
        botonIniciarSesion.setOnMouseEntered(e -> botonIniciarSesion.setStyle(estiloBotones + "-fx-background-color: #E64A19;"));
        botonIniciarSesion.setOnMouseExited(e -> botonIniciarSesion.setStyle(estiloBotones));
        botonRegistro.setOnMouseEntered(e -> botonRegistro.setStyle(estiloBotones + "-fx-background-color: #E64A19;"));
        botonRegistro.setOnMouseExited(e -> botonRegistro.setStyle(estiloBotones));

        // Funcion boton Iniciar Sesion:
        botonIniciarSesion.setOnAction(e -> {
            PantallaInicioSesion pantallaInicioSesion = new PantallaInicioSesion();
            pantallaInicioSesion.mostrarPantallaInicioSesion(primaryStage);
        });


        // Añadimos los botones a un VBox
        VBox layoutBotones = new VBox(20);
        layoutBotones.getChildren().addAll(botonIniciarSesion, botonRegistro);
        layoutBotones.setAlignment(Pos.CENTER);
        layoutBotones.setTranslateY(100); // Ajustamos la posición vertical

        // Usamos StackPane para superponer la imagen de fondo y los botones
        StackPane root = new StackPane();
        root.getChildren().addAll(imagenView, layoutBotones);

        // Creamos la escena
        Scene scene = new Scene(root, 600, 600);

        // Mostramos la escena en la ventana principal
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
