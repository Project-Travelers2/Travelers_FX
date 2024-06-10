package app.fx.elements;

import app.fx.Controller_V2;
import app.fx.Data.AIRPORT_INFORMATION;
import app.fx.HA.Queries;
import app.fx._env;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;

public class FlightTab extends Pane {

    private Controller_V2 controller;
    private AnchorPane ROOT;

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

    public FlightTab(AnchorPane root, Controller_V2 controller) {
        this.ROOT = root;
        this.controller = controller;
        this.FLIGHT_TAB = controller.FLIGHT_TAB;
        initialize();

        // Bind event handlers
        DEPARTURE.setOnAction(e -> onclick_departure(e));
        ARRIVAL.setOnAction(e -> onclick_arrival(e));
        DEPARTURE_DATE.setOnAction(e -> onclick_departure_datetime(e));
        ARRIVAL_DATE.setOnAction(e -> onclick_arrival_datetime(e));
        SEARCH.setOnAction(e -> onclick_search(e));
    }

    private void onclick_departure(ActionEvent e) {
        System.out.println("Departure button clicked");

        // 한국 민간공항 리스트 출력 테스트
        // 1 국가정보를 한국(KR)으로 하고 공항 리스트 가져오기
        String CountryCode = "KR";

        // 2 공항 리스트에서 민간공항 리스트 가져오기
        List<AIRPORT_INFORMATION> airport_informations = Queries.instance.airport_list(CountryCode);

        // 3 민간공항 리스트를 ListView에 할당하기
        // ListView 생성
        ListView<AIRPORT_INFORMATION> listView = new ListView<>();

        // ListView에 사용자 정의 항목 추가
        for (AIRPORT_INFORMATION airport_information : airport_informations) {
            listView.getItems().add(airport_information);
        }

        // 리스트 뷰에 해당 커스텀 객체의 동작 설정
        listView.setCellFactory(new Callback<ListView<AIRPORT_INFORMATION>, ListCell<AIRPORT_INFORMATION>>() {
            @Override
            public ListCell<AIRPORT_INFORMATION> call(ListView<AIRPORT_INFORMATION> airportInformationListView) {
                return new ListCell<AIRPORT_INFORMATION>() {
                    @Override
                    protected void updateItem(AIRPORT_INFORMATION item, boolean empty) {
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

        // ListView 항목 선택 이벤트 핸들러 설정 (클릭시 실행 이벤트 설정
        listView.setOnMouseClicked(this::onclick_select_departure);

        // ScrollPane 생성 및 ListView 추가
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(300, 200); // 고정된 크기 설정

        // ScrollPane을 AnchorPane에 추가
        ROOT.getChildren().add(scrollPane);
        AnchorPane.setTopAnchor(scrollPane, 50.0);
        AnchorPane.setLeftAnchor(scrollPane, 50.0);
    }

    /**
     * WBS: View1 - P1 - DEP_1_B
     * onclick select departure location button
     * @param mouseEvent select departure location click
     */
    private void onclick_select_departure(MouseEvent mouseEvent) {
        ListView<AIRPORT_INFORMATION> listView = (ListView<AIRPORT_INFORMATION>) mouseEvent.getSource();
        AIRPORT_INFORMATION selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            // 선택한 객체가 AIRPORT_INFORMATION인가?
            if (selectedItem instanceof AIRPORT_INFORMATION) {
                System.out.println("Selected item: " + selectedItem.toString());
                // 그러면 _env에 할당
                _env.departure_information = selectedItem;
            }

            //_env.departure_information = selectedItem

            // DEPARTURE 버튼 텍스트 설정
            DEPARTURE.setText(_env.departure_information.toString());

            // scrollPane 제거
            ScrollPane scrollPane = (ScrollPane) listView.getParent().getParent().getParent();
            ROOT.getChildren().remove(scrollPane);
        }
    }

    private void onclick_arrival(ActionEvent e) {
        System.out.println("arrival button clicked");

        // 한국 민간공항 리스트 출력 테스트
        // 1 국가정보를 한국(KR)으로 하고 공항 리스트 가져오기
        String CountryCode = "KR"; // TODO: diff

        // 2 공항 리스트에서 민간공항 리스트 가져오기
        List<AIRPORT_INFORMATION> airport_informations = Queries.instance.airport_list(CountryCode);

        // 3 민간공항 리스트를 ListView에 할당하기
        // ListView 생성
        ListView<AIRPORT_INFORMATION> listView = new ListView<>();

        // ListView에 사용자 정의 항목 추가
        for (AIRPORT_INFORMATION airport_information : airport_informations) {
            listView.getItems().add(airport_information);
        }

        // 리스트 뷰에 해당 커스텀 객체의 동작 설정
        listView.setCellFactory(new Callback<ListView<AIRPORT_INFORMATION>, ListCell<AIRPORT_INFORMATION>>() {
            @Override
            public ListCell<AIRPORT_INFORMATION> call(ListView<AIRPORT_INFORMATION> airportInformationListView) {
                return new ListCell<AIRPORT_INFORMATION>() {
                    @Override
                    protected void updateItem(AIRPORT_INFORMATION item, boolean empty) {
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

        // ListView 항목 선택 이벤트 핸들러 설정 (클릭시 실행 이벤트 설정
        listView.setOnMouseClicked(this::onclick_select_arrival); // TODO: diff

        // ScrollPane 생성 및 ListView 추가
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(listView);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefSize(300, 200); // 고정된 크기 설정

        // ScrollPane을 AnchorPane에 추가
        ROOT.getChildren().add(scrollPane);
        AnchorPane.setTopAnchor(scrollPane, 50.0);
        AnchorPane.setLeftAnchor(scrollPane, 300.0); // TODO: diff
    }

    /**
     * WBS: View1 - P1 - ARR_1_B
     * onclick select arrival location button
     * @param mouseEvent select arrival location button click
     */
    private void onclick_select_arrival(MouseEvent mouseEvent) {
        ListView<AIRPORT_INFORMATION> listView = (ListView<AIRPORT_INFORMATION>) mouseEvent.getSource();
        AIRPORT_INFORMATION selectedItem = listView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {

            // 선택한 객체가 AIRPORT_INFORMATION인가?
            if (selectedItem instanceof AIRPORT_INFORMATION) {
                System.out.println("Selected item: " + selectedItem.toString());
                // 그러면 _env에 할당
                _env.arrival_information = selectedItem; // TODO: diff
            }

            //_env.departure_information = selectedItem

            // DEPARTURE 버튼 텍스트 설정
            ARRIVAL.setText(_env.arrival_information.toString()); // TODO: diff

            // scrollPane 제거
            ScrollPane scrollPane = (ScrollPane) listView.getParent().getParent().getParent();
            ROOT.getChildren().remove(scrollPane);
        }
    }


    /**
     * WBS: View1 - P1 - SDT_B
     * WBS: View1 - P1 - SDT_1_B
     * onclick departure datetime datepicker
     * @param e departure datetime select
     */
    private void onclick_departure_datetime(ActionEvent e) {
        System.out.println("departure datetime datePicker selected");

        // DatePicker에서 선택한 날짜를 가져옴
        LocalDate selectedDate = DEPARTURE_DATE.getValue();

        if (selectedDate == null) {
            System.out.println("No date selected");
            return;
        }

        _env.departure_date = selectedDate;
    }

    /**
     * WBS: View1 - P1 - END_B
     * WBS: View1 - P1 - END_1_B
     * onclick arrival datetime datePicker
     * @param event arrival datetime select
     */
    private void onclick_arrival_datetime(ActionEvent event) {
        System.out.println("arrival datetime datePicker selected");

        // DatePicker에서 선택한 날짜를 가져옴
        LocalDate selectedDate = ARRIVAL_DATE.getValue(); // TODO: diff

        if (selectedDate == null) {
            System.out.println("No date selected");
            return;
        }

        _env.arrival_date = selectedDate; // TODO: diff
    }

    private void onclick_search(ActionEvent e) {
        System.out.println("Serach button clicked");

        // 현재까지 선택한 정보들 출력하기
        System.out.println("=============================");
        System.out.println("현재 선택한 정보들");
        System.out.println("여행 정보 : " + _env.selected_festival);
        System.out.println("출발 공항 : " + _env.departure_information);
        System.out.println("도착 공항 : " + _env.arrival_information);
        System.out.println("출발일 : " + _env.departure_date);
        System.out.println("도착일 : " + _env.arrival_date);
        System.out.println("=============================");

        if (isCanNavigate()) {
            System.out.println("안내 조건을 만족했습니다. 다음 단계로 넘어갑니다.");
        } else {
            System.out.println("아직 선택하지 않은 조건이 있습니다.");
            return;
        }
    }

    private boolean isCanNavigate() {
        if (_env.selected_festival == null ||
                _env.departure_information == null ||
                _env.arrival_information == null ||
                _env.departure_date == null ||
                _env.arrival_date == null) {
            return false;
        } else {
            return true;
        }
    }

}
