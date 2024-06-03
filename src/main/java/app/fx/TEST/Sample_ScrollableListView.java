package app.fx.TEST;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sample_ScrollableListView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // ListView 생성
        ListView<String> listView = new ListView<>();

        // ListView에 항목 추가
        listView.getItems().addAll(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9",
                "Item 10"
        );

        // ScrollPane 생성 및 ListView 추가
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);  // ScrollPane 너비가 ListView 너비와 맞도록 설정

        // 고정된 크기 설정
        scrollPane.setPrefSize(300, 200);

        // 레이아웃 생성
        VBox vbox = new VBox(scrollPane);

        // Scene 생성 및 설정
        Scene scene = new Scene(vbox, 300, 200);

        // Stage 설정
        primaryStage.setTitle("JavaFX Scrollable ListView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
