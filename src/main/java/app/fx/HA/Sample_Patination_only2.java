package app.fx.HA;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Sample_Patination_only2 extends Application {

    private static final int NUM_PAGES = 10; // 총 페이지 수
    private static final int ROWS = 2;
    private static final int COLS = 3;

    @Override
    public void start(Stage primaryStage) {
        // Initialize Pagination
        Pagination pagination = new Pagination(NUM_PAGES);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public GridPane call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });

        // Set up the Scene and Stage
        Scene scene = new Scene(pagination, 1600, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pagination Example");
        primaryStage.show();
    }

    private GridPane createPage(int pageIndex) {
        // Initialize GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Set column and row constraints
        for (int col = 0; col < COLS; col++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(100.0 / COLS);
            gridPane.getColumnConstraints().add(colConstraints);
        }

        for (int row = 0; row < ROWS; row++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(100.0 / ROWS);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        // Add buttons to GridPane
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Button button = new Button("Page " + (pageIndex + 1) + " Button " + ((row * COLS) + col + 1));
                button.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
                gridPane.add(button, col, row);
            }
        }

        return gridPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

