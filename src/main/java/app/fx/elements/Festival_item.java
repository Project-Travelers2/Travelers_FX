package app.fx.elements;

import app.fx.Data.FESTIVAL_INFORMATION;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class Festival_item extends Pane {

    private FESTIVAL_INFORMATION fest_info;
    public Label description;
    public Button reserve;
    public ImageView imageView;

    public Festival_item() {
        this.setPrefSize(515, 280);
//        this.setFont(new Font("Arial", 24));
        this.setId("FESTIVAL_ITEM");

        // Initialize ImageView
        imageView = new ImageView();
        imageView.setFitHeight(280.0);
        imageView.setFitWidth(514.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // Initialize Label
        description = new Label();
        description.setAlignment(Pos.CENTER);
        description.setPrefHeight(210.0);
        description.setPrefWidth(514.0);
        description.setText("Label");
        description.setFont(new Font(25.0));

        // Initialize Button
        reserve = new Button();
        reserve.setLayoutY(208.0);
        reserve.setMnemonicParsing(false);
        reserve.setPrefHeight(76.0);
        reserve.setPrefWidth(514.0);
        reserve.setText("reserve");
        reserve.setFont(new Font(32.0));

        // Add children to Pane
        this.getChildren().addAll(imageView, description, reserve);
    }

    public Festival_item(FESTIVAL_INFORMATION fest_info) {
        this();
        this.fest_info = fest_info;
//        this.setText(fest_info.festival_name);
        
        description.setText(fest_info.festival_name);
        reserve.setText("reserve");
//        imageView.setImage(); // 이미지 세팅하기
    }
}
