package app.fx.HA;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Sample_Pagination_only extends Application {

    private static final int ITEMS_PER_PAGE = 10;
    private static final int TOTAL_ITEMS = 50; // 총 아이템 수

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pagination pagination = new Pagination((TOTAL_ITEMS / ITEMS_PER_PAGE + 1), 0);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public GridPane call(Integer pageIndex) {
                return new GridPane();
//                return createPage(pageIndex);
            }
//            public VBox call(Integer pageIndex) {
//                return createPage(pageIndex);
//            }
        });

        VBox root = new VBox(pagination);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("JavaFX Pagination Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * ITEMS_PER_PAGE;
        for (int i = page; i < page + ITEMS_PER_PAGE && i < TOTAL_ITEMS; i++) {
            box.getChildren().add(new Label("Item " + (i + 1)));
        }
        return box;
    }
}