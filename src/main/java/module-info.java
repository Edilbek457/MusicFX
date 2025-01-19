module org.example.musicfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.example.musicfx to javafx.fxml;
    exports org.example.musicfx;
}