package app.fx.elements;

import app.fx.Data.FESTIVALS;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Festival_item extends Pane {

    private FESTIVALS fest_info;
    public Label description;
    public Button reserve;
    public ImageView imageView;

    public Festival_item() {
        this.setPrefSize(517, 260);
//        this.setFont(new Font("Arial", 24));
        this.setId("FESTIVAL_ITEM");

        // Initialize ImageView
        imageView = new ImageView();
        imageView.setLayoutY(0.0);
        imageView.setFitHeight(250.0);
        imageView.setFitWidth(517.0);
        // 이미지 비율 유지
        imageView.setPreserveRatio(false);
        imageView.setPickOnBounds(true);
        imageView.setSmooth(true);

        // Initialize Label
        description = new Label();
        description.setAlignment(Pos.CENTER);
        description.setPrefHeight(210.0);
        description.setPrefWidth(517.0);
        description.setText("Label");
        description.setFont(new Font(25.0));

        // Initialize Button
        reserve = new Button();
        reserve.setLayoutY(210.0);
        reserve.setMnemonicParsing(false);
        reserve.setPrefHeight(20.0);
        reserve.setPrefWidth(517.0);
        reserve.setText("reserve");
        reserve.setFont(new Font(22.0));

        // Add children to Pane
        this.getChildren().addAll(imageView, description, reserve);
    }

    public Festival_item(FESTIVALS fest_info) {
        this();
        this.fest_info = fest_info;
//        this.setText(fest_info.festival_name);
        
//        description.setText(fest_info.festival_name);
        description.setText("");
        reserve.setText("reserve");

        String imagePath = fest_info.image_path;
        Image image = new Image(getClass().getResource(imagePath).toString());

        imageView.setImage(new Image(getClass().getResource(fest_info.image_path).toString())); // 이미지 세팅하기
    }

    public Festival_item(String test) {
        this();
    }

    public FESTIVALS getFest_info() {
        return fest_info;
    }
}
