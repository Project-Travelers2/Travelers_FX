package app.fx.HA;

import app.fx.V1_Controller;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Main_HA extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/app/fx/Merge/merge_travel_items.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1600, 900);
        V1_Controller controller = fxmlloader.getController();
        bindTestKey(scene, controller);

        stage.setTitle("Travel items");
        stage.setScene(scene);
        stage.show();
    }

    // 테스트용 키 바인딩
    private static void bindTestKey(@NotNull Scene scene, V1_Controller controller) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case A:
                        controller.pageDown();
                        break;
                    case D:
                        controller.pageUp();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
