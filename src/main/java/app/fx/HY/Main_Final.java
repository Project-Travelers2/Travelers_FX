package app.fx.HY;

import app.fx.Controller_V2;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Main_Final extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/app/fx/HY/merge_travel_items_Final.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1600, 900);
        Controller_V2 controller = fxmlloader.getController();
        bindTestKey(scene, controller);

        stage.setTitle("Travel items");
        stage.setScene(scene);
        stage.show();
    }

    // 테스트용 키 바인딩
    private static void bindTestKey(@NotNull Scene scene, Controller_V2 controller) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                controller.handleKey(keyEvent);
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
