module org.example.padelmadridpro {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.padelmadridpro to javafx.fxml;
    exports org.example.padelmadridpro;
}