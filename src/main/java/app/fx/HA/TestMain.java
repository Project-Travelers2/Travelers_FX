package app.fx.HA;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TestMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        Scene scene = new Scene(root, 300, 250);

        Button addButton = new Button("Add New Button");
        root.getChildren().add(addButton);

        addButton.setOnAction(event -> {
            // 다른 FXML 파일에서 버튼을 로드하여 추가합니다.
            Platform.runLater(() -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/Merge/new_button.fxml"));
                    Parent newButtonParent = loader.load();

                    NewButtonController controller = loader.getController();
                    Button newButton = controller.getNewButton();
                    root.getChildren().add(newButton);

                    // 새로운 버튼 제거 이벤트 추가
                    newButton.setOnAction(e -> {
                        root.getChildren().remove(newButton);
                        System.out.println("New button removed!");
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });



        primaryStage.setTitle("Dynamic Button Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}