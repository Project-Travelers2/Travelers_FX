package app.fx.elements;

import app.fx._env;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class DetailedImageView extends Pane {

    private Festival_item item;
    private final Rectangle outside;
    private final ImageView imgView;
    private final TextArea title;
    private final TextArea desc;
    private final Button reserve;

    public DetailedImageView() {
        // Set Pane properties
        this.setPrefHeight(900.0);
        this.setPrefWidth(1600.0);

        // Initialize OUTSIDE Rectangle
        outside = new Rectangle(1600.0, 900.0);
        outside.setArcHeight(5.0);
        outside.setArcWidth(5.0);
        outside.setFill(Color.web("#1f93ff38"));
        outside.setId("OUTSIDE");
//        outside.setStroke(Color.BLACK);
//        outside.setStrokeType(Rectangle.StrokeType.INSIDE);
//        outside.setStrokeType(Rectangle.StrokeType.INSIDE);

//        // Add mouse click event to OUTSIDE
//        outside.setOnMouseClicked(event -> handleOutsideClick(event));

        // Initialize inner Pane
        Pane innerPane = new Pane();
        innerPane.setLayoutX(160.0);
        innerPane.setLayoutY(90.0);
        innerPane.setPrefHeight(720.0);
        innerPane.setPrefWidth(1280.0);

        // Initialize inner Rectangle
        Rectangle innerRectangle = new Rectangle(1280.0, 720.0);
        innerRectangle.setArcHeight(5.0);
        innerRectangle.setArcWidth(5.0);
        innerRectangle.setFill(Color.web("#ffffffff"));
//        innerRectangle.setStroke(Color.BLACK);
//        innerRectangle.setStrokeType(Rectangle.StrokeType.INSIDE);



        // Initialize ImageView
        imgView = new ImageView();
        imgView.setFitHeight(720.0);
        imgView.setFitWidth(1280.0);
        imgView.setPickOnBounds(true);
        imgView.setPreserveRatio(true);

        // Initialize GridPane
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setLayoutX(980.0);
        gridPane.setPrefHeight(720.0);
        gridPane.setPrefWidth(300.0);
        gridPane.setVgap(10.0);

        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        colConstraints.setMinWidth(10.0);
        colConstraints.setPrefWidth(100.0);
        gridPane.getColumnConstraints().add(colConstraints);

        RowConstraints row1 = new RowConstraints();
        row1.setMaxHeight(235.0);
        row1.setMinHeight(0.0);
        row1.setPrefHeight(150.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        RowConstraints row2 = new RowConstraints();
        row2.setMaxHeight(500.0);
        row2.setMinHeight(10.0);
        row2.setPrefHeight(100.0);
        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        RowConstraints row3 = new RowConstraints();
        row3.setMaxHeight(500.0);
        row3.setMinHeight(10.0);
        row3.setPrefHeight(330.0);
        row3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        RowConstraints row4 = new RowConstraints();
        row4.setMaxHeight(190.0);
        row4.setMinHeight(10.0);
        row4.setPrefHeight(70.0);
        row4.setVgrow(javafx.scene.layout.Priority.SOMETIMES);


        gridPane.getRowConstraints().addAll(row1, row2, row3, row4);

        //
        Pane outlineRec = new Pane();
        outlineRec.setLayoutX(980);
        outlineRec.setLayoutY(0);
        outlineRec.setPrefHeight(720);
        outlineRec.setPrefWidth(300);
        outlineRec.setStyle("-fx-background-color: rgba(248,248,248,0.95)");

        // Initialize TITLE Label
        title = new TextArea("축제 제목");
        title.setPrefHeight(500.0);
        title.setPrefWidth(300.0);
        title.setWrapText(true);
        title.setFont(Font.font("나눔바른고딕",20));
        GridPane.setRowIndex(title, 1);
        // Set the background to transparent and the text color to black
        title.opacityProperty().set(0.8);
        title.setStyle("-fx-background-color: transparent; " +
                "-fx-background-insets: 0; " +
                "-fx-background-radius: 0; " +
                "-fx-border-color: transparent;"+
                "-fx-border-width: 1 1 1 1");




        // Initialize DESC Label
        desc = new TextArea("축제 설명");
        desc.setPrefHeight(500.0);
        desc.setPrefWidth(300.0);
        desc.setWrapText(true);
        desc.setFont(Font.font("나눔바른고딕",15));
        desc.opacityProperty().set(0.7);
        GridPane.setRowIndex(desc, 2);


        // Initialize RESERVE Button
        reserve = new Button("예약하기");
        reserve.setAlignment(Pos.CENTER);
        reserve.setMnemonicParsing(false);
        reserve.setPrefHeight(70.0);
        reserve.setPrefWidth(280.0);
        reserve.setFont(Font.font("배달의민족 도현",20));
        reserve.setStyle("-fx-background-color: rgba(112,183,250,0.86)");
        reserve.setTextFill(Paint.valueOf("white"));
        GridPane.setRowIndex(reserve, 3);



        // Add mouse click event to RESERVE
        reserve.setOnMouseClicked(event -> handleReserveClick(event));

        // Add children to GridPane
        gridPane.getChildren().addAll( title, desc, reserve);

        // Add padding to GridPane
        gridPane.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));


        // Add children to inner Pane
        innerPane.getChildren().addAll(innerRectangle,imgView,outlineRec, gridPane);

        // Add children to root Pane
        this.getChildren().addAll(outside, innerPane);
    }

    public DetailedImageView(Festival_item item) {
        this();
        this.item = item;
        outside.setOnMouseClicked(event -> handleOutsideClick(event));

        imgView.setImage(item.imageView.getImage());
        title.setText(item.getFest_info().festival_name);
        desc.setText(item.getFest_info().description);
        reserve.setOnMouseClicked(event -> handleReserveClick(event));
    }

    // Event handler for OUTSIDE click
    private void handleOutsideClick(MouseEvent event) {
        // Close the current window
        if (this.getParent() instanceof Pane) {
            AnchorPane parent = (AnchorPane)this.getParent();
            parent.getChildren().remove(this);
        }
    }

    // Event handler for RESERVE click
    private void handleReserveClick(MouseEvent event) {
        // Close the current window
        _env.selected_festival = item;
        if (this.getParent() instanceof Pane) {
            AnchorPane parent = (AnchorPane)this.getParent();
            parent.getChildren().remove(this);
        }
    }

    // Methods to set content
    public void setImage(Image image) {
        imgView.setImage(image);
    }

    public void setTitle(String titleText) {
        title.setText(titleText);
    }

    public void setDesc(String descText) {
        desc.setText(descText);
    }



    public static void main(String[] args) {
//        Application.launch(args);
    }
}
