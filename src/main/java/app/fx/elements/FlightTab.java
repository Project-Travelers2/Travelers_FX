package app.fx.elements;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class FlightTab extends Pane {

    @FXML
    public Pane FLIGHT_TAB;

    @FXML
    public Button DEPARTURE;

    @FXML
    public Button ARRIVAL;

    @FXML
    public Button SEARCH;

    @FXML
    public DatePicker DEPARTURE_DATE;

    @FXML
    public DatePicker ARRIVAL_DATE;

    public FlightTab(Pane flightTab) {
        this.FLIGHT_TAB = flightTab;
        initialize();
    }

    private void initialize() {
        // Initialize and add the first Rectangle
        Rectangle backgroundRect = new Rectangle(1600.0, 150.0);
        backgroundRect.setArcHeight(5.0);
        backgroundRect.setArcWidth(5.0);
        backgroundRect.setFill(Color.web("#f6faff"));
        backgroundRect.setStroke(Color.BLACK);
//        backgroundRect.setStrokeType(Rectangle.StrokeType.INSIDE);
        FLIGHT_TAB.getChildren().add(backgroundRect);

        // Initialize the first Pane
        Pane departurePane = new Pane();
        departurePane.setLayoutX(40.0);
        departurePane.setPrefHeight(150.0);
        departurePane.setPrefWidth(200.0);
        departurePane.setPadding(new javafx.geometry.Insets(50.0, 0, 20.0, 0));

        // Initialize and add the departure Button
        DEPARTURE = new Button("서울");
        DEPARTURE.setLayoutX(40.0);
        DEPARTURE.setLayoutY(25.0);
        DEPARTURE.setMaxHeight(Double.MAX_VALUE);
        DEPARTURE.setMaxWidth(Double.MAX_VALUE);
        DEPARTURE.setPrefHeight(100.0);
        DEPARTURE.setPrefWidth(120.0);
        departurePane.getChildren().add(DEPARTURE);
        FLIGHT_TAB.getChildren().add(departurePane);

        // Initialize and add the second Rectangle
        Rectangle midRect = new Rectangle(60.0, 60.0);
        midRect.setArcHeight(5.0);
        midRect.setArcWidth(5.0);
        midRect.setFill(Color.web("#a3d3ff"));
        midRect.setStroke(Color.BLACK);
//        midRect.setStrokeType(Rectangle.StrokeType.INSIDE);
        midRect.setLayoutX(264.0);
        midRect.setLayoutY(46.0);
        FLIGHT_TAB.getChildren().add(midRect);

        // Initialize the second Pane
        Pane arrivalPane = new Pane();
        arrivalPane.setLayoutX(344.0);
        arrivalPane.setLayoutY(2.0);
        arrivalPane.setPrefHeight(150.0);
        arrivalPane.setPrefWidth(200.0);
        arrivalPane.setPadding(new javafx.geometry.Insets(50.0, 0, 20.0, 0));

        // Initialize and add the arrival Button
        ARRIVAL = new Button("모든 곳");
        ARRIVAL.setLayoutX(40.0);
        ARRIVAL.setLayoutY(25.0);
        ARRIVAL.setMaxHeight(Double.MAX_VALUE);
        ARRIVAL.setMaxWidth(Double.MAX_VALUE);
        ARRIVAL.setPrefHeight(100.0);
        ARRIVAL.setPrefWidth(120.0);
        arrivalPane.getChildren().add(ARRIVAL);
        FLIGHT_TAB.getChildren().add(arrivalPane);

        // Initialize and add the search Button
        SEARCH = new Button("항공권 검색");
        SEARCH.setLayoutX(1370.0);
        SEARCH.setLayoutY(51.0);
        SEARCH.setPrefHeight(60.0);
        SEARCH.setPrefWidth(180.0);
        FLIGHT_TAB.getChildren().add(SEARCH);

        // Initialize and add the departure DatePicker
        DEPARTURE_DATE = new DatePicker();
        DEPARTURE_DATE.setLayoutX(555.0);
        DEPARTURE_DATE.setLayoutY(50.0);
        DEPARTURE_DATE.setPrefHeight(60.0);
        DEPARTURE_DATE.setPrefWidth(390.0);
        DEPARTURE_DATE.setPromptText("출발일");
        FLIGHT_TAB.getChildren().add(DEPARTURE_DATE);

        // Initialize and add the arrival DatePicker
        ARRIVAL_DATE = new DatePicker();
        ARRIVAL_DATE.setLayoutX(960.0);
        ARRIVAL_DATE.setLayoutY(50.0);
        ARRIVAL_DATE.setPrefHeight(60.0);
        ARRIVAL_DATE.setPrefWidth(390.0);
        ARRIVAL_DATE.setPromptText("도착일");
        FLIGHT_TAB.getChildren().add(ARRIVAL_DATE);
    }

}
