package app.fx.HA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_HA extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/app/fx/HA/travel_items_HA.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1600, 900);
        stage.setTitle("Travel items");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
