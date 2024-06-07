package app.fx.elements;

import app.fx.Data.USERS;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.List;

public class User_Pane extends GridPane {

    public List<Button> grids = new ArrayList<Button>();
    public USERS selectedUser;


    // 사용자 단계에 따라 다른 생성이 필요하다.
    // 기본 패널 수준만 남긴다.
    public User_Pane() {
        // GridPane 설정
        this.setId("USER_PANE");
        this.setAlignment(javafx.geometry.Pos.CENTER);
        this.setHgap(10.0);
        this.setLayoutX(1250.0);
        this.setLayoutY(0);
        this.setPrefSize(300.0, 50.0);

        // ColumnConstraints 설정
        ColumnConstraints col1 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col1.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col2 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col2.setHgrow(Priority.SOMETIMES);
        ColumnConstraints col3 = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        col3.setHgrow(Priority.SOMETIMES);
        this.getColumnConstraints().addAll(col1, col2, col3);

        // RowConstraints 설정
        RowConstraints row1 = new RowConstraints(30, 30, Double.MAX_VALUE);
        row1.setVgrow(Priority.SOMETIMES);
        this.getRowConstraints().add(row1);

        // Padding 설정
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    public Button getElement(int index) throws IndexOutOfBoundsException{
        return null;
    }
}