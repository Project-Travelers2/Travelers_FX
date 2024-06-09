package app.fx.elements;

import app.fx.V1_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Title_tab extends Pane {

    private final V1_Controller controller;

    @FXML
    private Pane TitleBar;

    @FXML
    private Button HOME;

    @FXML
    private Label titleLabel;

    private void initialize() {
        // Initialize and add the background Rectangle
        Rectangle backgroundRect = new Rectangle(1600.0, 50.0);
        backgroundRect.setArcHeight(5.0);
        backgroundRect.setArcWidth(5.0);
        backgroundRect.setFill(Color.web("#8bc7ff"));
        backgroundRect.setStroke(Color.BLACK);
//        backgroundRect.setStrokeType(Rectangle.StrokeType.INSIDE);
        TitleBar.getChildren().add(backgroundRect);

        // Initialize the inner Pane
        Pane innerPane = new Pane();
        innerPane.setLayoutX(40.0);
        innerPane.setLayoutY(-2.0);
        innerPane.setPrefHeight(50.0);
        innerPane.setPrefWidth(280.0);

        // Initialize and add the HOME Button
        HOME = new Button("HOME");
        HOME.setLayoutY(7.0);
        HOME.setPrefHeight(40.0);
        HOME.setPrefWidth(100.0);
        HOME.setMnemonicParsing(false);
        innerPane.getChildren().add(HOME);

        // Initialize and add the Separator
        Separator separator = new Separator();
        separator.setLayoutX(259.0);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(50.0);
        innerPane.getChildren().add(separator);

        // Initialize and add the Label
        titleLabel = new Label("여행상품");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(95.0);
        titleLabel.setLayoutY(2.0);
        titleLabel.setPrefHeight(51.0);
        titleLabel.setPrefWidth(165.0);
        titleLabel.setFont(new Font("D2Coding", 30.0));
        innerPane.getChildren().add(titleLabel);

        TitleBar.getChildren().add(innerPane);
    }

    public Title_tab(Pane titleTab, V1_Controller controller) {
        this.TitleBar = titleTab;
        this.controller = controller;
        initialize();

        HOME.setOnAction(e -> onclick_home());
    }

    /**
     * WBS: View1 - P1 - HOME_B
     * onclick home event
     */
    private void onclick_home() {
        System.out.println("Home button clicked");
        controller.menuTab.onclick_all_festivals();
    }


}
