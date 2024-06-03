package app.fx.TEST;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Sample_CustomListView extends Application {
    @Override
    public void start(Stage primaryStage) {
        // ListView 생성
        ListView<Sample_CustomItem> listView = new ListView<>();

        // ListView에 사용자 정의 항목 추가
        listView.getItems().addAll(
                new Sample_CustomItem("Item 1", 100),
                new Sample_CustomItem("Item 2", 200),
                new Sample_CustomItem("Item 3", 300),
                new Sample_CustomItem("Item 4", 400),
                new Sample_CustomItem("Item 5", 500)
        );

        // 셀 팩토리 설정
        listView.setCellFactory(new Callback<ListView<Sample_CustomItem>, ListCell<Sample_CustomItem>>() {
            @Override
            public ListCell<Sample_CustomItem> call(ListView<Sample_CustomItem> param) {
                return new ListCell<Sample_CustomItem>() {
                    @Override
                    protected void updateItem(Sample_CustomItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.toString());
                        }
                    }
                };
            }
        });

        // ScrollPane 생성 및 ListView 추가
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);

        // 고정된 크기 설정
        scrollPane.setPrefSize(300, 200);

        // 레이아웃 생성
        VBox vbox = new VBox(scrollPane);

        // Scene 생성 및 설정
        Scene scene = new Scene(vbox, 300, 200);

        // Stage 설정
        primaryStage.setTitle("JavaFX Custom ListView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
