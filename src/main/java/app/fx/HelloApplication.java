package app.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String apiKey = _env.getEnv("API_KEY");
        String dbUrl = _env.getEnv("DATABASE_URL");

        System.out.println(apiKey);
        System.out.println(dbUrl);
        System.out.println("Hello");

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("resolution.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}