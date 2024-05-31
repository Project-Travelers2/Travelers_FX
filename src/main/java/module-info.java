module app.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.dotenv;

    opens app.fx to javafx.fxml;
    exports app.fx;
}