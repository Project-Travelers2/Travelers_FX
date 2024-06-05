module app.fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.dotenv;
    requires java.sql;
    requires annotations;
    requires commons.csv;
    requires ojdbc8;

    opens app.fx to javafx.fxml;
    opens app.fx.HY to javafx.fxml;
    opens app.fx.HA to javafx.fxml;
    opens app.fx.Merge to javafx.fxml;

    exports app.fx;
    exports app.fx.HY;
    exports app.fx.HA;
    exports app.fx.TEST;
    opens app.fx.TEST to javafx.fxml;

}

