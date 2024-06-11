package app.fx.elements;

import app.fx.Control.ControlEvent;
import app.fx.Controller_V2;
import app.fx.Data.AIRPORT_INFORMATION;
import app.fx.Data.EventCode;
import app.fx.Data.FESTIVALS;
import app.fx.HA.Queries;
import app.fx._env;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FlightTab extends Pane {

    private Controller_V2 controller;
    private AnchorPane ROOT;

    @FXML
    public Pane FLIGHT_TAB;

    @FXML
    public Button DEPARTURE;
    private ScrollPane departure_scroll;

    @FXML
    public Button ARRIVAL;
    private ScrollPane arrival_scroll;

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
//        backgroundRect.setStroke(Color.BLACK);
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
        DEPARTURE.setFont(Font.font("배달의민족 도현",25));
        DEPARTURE.setTextFill(Paint.valueOf("black"));
        DEPARTURE.setStyle("-fx-background-color: #f6faff;");

        // Initialize and add the second Rectangle
        Rectangle midRect = new Rectangle(60.0, 60.0);
        midRect.setArcHeight(5.0);
        midRect.setArcWidth(5.0);
        midRect.setFill(Color.web("#f6faff"));
//        midRect.setStroke(Color.BLACK);
//        midRect.setStrokeType(Rectangle.StrokeType.INSIDE);
        midRect.setLayoutX(264.0);
        midRect.setLayoutY(46.0);
//        midRect.setStyle("-fx-background-color: #f6faff");
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
        ARRIVAL.setFont(Font.font("배달의민족 도현", 25));
        ARRIVAL.setTextFill(Paint.valueOf("black"));
        ARRIVAL.setStyle("-fx-background-color: #f6faff");

        // Initialize and add the search Button
        SEARCH = new Button("항공권 검색");
        SEARCH.setLayoutX(1370.0);
        SEARCH.setLayoutY(51.0);
        SEARCH.setPrefHeight(60.0);
        SEARCH.setPrefWidth(180.0);
        SEARCH.setFont(Font.font("배달의민족 도현",20));
        SEARCH.setTextFill(Paint.valueOf("white"));
        SEARCH.setStyle("-fx-background-color: #89c3fb");
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

    private String cssColor = "";
    private LocalDate startDate;
    private LocalDate endDate;

    // TODO: 13131 예약정보 초기화
    public void resetReservation() {
        setDateRange(LocalDate.now(), LocalDate.now(), 0);
    }

    // TODO: 예약정보 업데이트
    public void setReservation(Festival_item selectedFestival) {
        FESTIVALS info = selectedFestival.getFest_info();
        setDateRange(info.start_date, info.end_date, 1);
        DEPARTURE_DATE.setValue(info.start_date);
        ARRIVAL_DATE.setValue(info.end_date);
    }


    private void setDateRange(LocalDate start, LocalDate end, int cssCode) {
        this.startDate = start;
        this.endDate = end;

        switch (cssCode) {
            case 0:
                cssColor = "-fx-background-color: #ffcccc;";
                break;
            case 1:
                cssColor = "-fx-background-color: #ccccff;";
                break;
        }
    }

    public FlightTab(AnchorPane root, Controller_V2 controller) {
        this.ROOT = root;
        this.controller = controller;
        this.FLIGHT_TAB = controller.FLIGHT_TAB;
        initialize();

        // Bind event handlers
        DEPARTURE.setOnAction(e -> onclick_departure(new ControlEvent(e, EventCode.FLIGHT_DEPARTURE)) );
        ARRIVAL.setOnAction(e -> onclick_arrival(new ControlEvent(e, EventCode.FLIGHT_ARRIVAL)) );

        LocalDate now = LocalDate.now();
//        LocalDate plus7 = now.plusDays(7);
        setDateRange(now, now, 0);

        DEPARTURE_DATE.setOnMouseClicked(e -> onclick_departure_datetime(new ControlEvent(e, EventCode.FLIGHT_DEPARTURE_DATE_CLICK)) );
        DEPARTURE_DATE.setOnAction(e -> onselect_departure_datetime(new ControlEvent(e, EventCode.FLIGHT_DEPARTURE_DATE_SELECT)) );
        // dayCellFactory 설정
        DEPARTURE_DATE.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // 특정 날짜 범위에 색깔 입히기
                        if (item != null && !empty && (item.isEqual(startDate) || item.isEqual(endDate) || (item.isAfter(startDate) && item.isBefore(endDate)))) {
                            setStyle(cssColor);  // 연한 빨간색 배경
                        }
                    }
                };
            }
        });

        ARRIVAL_DATE.setOnMouseClicked(e -> onclick_arrival_datetime(new ControlEvent(e, EventCode.FLIGHT_ARRIVAL_DATE_CLICK)) );
        ARRIVAL_DATE.setOnAction(e -> onselect_arrival_datetime(new ControlEvent(e, EventCode.FLIGHT_ARRIVAL_DATE_SELECT)) );
        // dayCellFactory 설정
        ARRIVAL_DATE.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // 특정 날짜 범위에 색깔 입히기
                        if (item != null && !empty && (item.isEqual(startDate) || item.isEqual(endDate) || (item.isAfter(startDate) && item.isBefore(endDate)))) {
//                            setStyle("-fx-background-color: #ffcccc;");  // 연한 빨간색 배경
                            setStyle(cssColor);  // 연한 빨간색 배경
                        }
                    }
                };
            }
        });

        SEARCH.setOnAction(e -> onclick_search(new ControlEvent(e, EventCode.FLIGHT_SEARCH)) );
    }

    public void receive(ControlEvent e) {
        if (e.getEventCode() != EventCode.FLIGHT_DEPARTURE) {
            abort_departure();
        }

        if (e.getEventCode() != EventCode.FLIGHT_ARRIVAL) {
            abort_arrival();
        }

        if (e.getEventCode() != EventCode.FLIGHT_DEPARTURE_DATE_CLICK ||
            e.getEventCode() != EventCode.FLIGHT_DEPARTURE_DATE_SELECT) {
            abort_departure_datetime();
        }

        if (e.getEventCode() != EventCode.FLIGHT_ARRIVAL_DATE_CLICK ||
            e.getEventCode() != EventCode.FLIGHT_ARRIVAL_DATE_SELECT) {
            abort_arrival_datetime();
        }
    }

    // <editor-fold desc="#on departure">
    private void onclick_departure(ControlEvent e) {
        System.out.println("Departure button clicked");
        controller.catchEvent(e); // Broadcasting

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
        departure_scroll = new ScrollPane();
        departure_scroll.setContent(listView);
        departure_scroll.setFitToWidth(true);
        departure_scroll.setPrefSize(300, 200); // 고정된 크기 설정

        // ScrollPane을 AnchorPane에 추가
        ROOT.getChildren().add(departure_scroll);
        AnchorPane.setTopAnchor(departure_scroll, 50.0);
        AnchorPane.setLeftAnchor(departure_scroll, 50.0);
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
            abort_departure();
        }
    }

    private void abort_departure() {
        if (departure_scroll != null) {
            ROOT.getChildren().remove(departure_scroll);
        }
    }
    // </editor-fold>

    // <editor-fold desc="#on arrival">
    private void onclick_arrival(ControlEvent e) {
        System.out.println("arrival button clicked");
        controller.catchEvent(e); // Broadcasting

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
        arrival_scroll = new ScrollPane();
        arrival_scroll.setContent(listView);
        arrival_scroll.setFitToWidth(true);
        arrival_scroll.setPrefSize(300, 200); // 고정된 크기 설정

        // ScrollPane을 AnchorPane에 추가
        ROOT.getChildren().add(arrival_scroll);
        AnchorPane.setTopAnchor(arrival_scroll, 50.0);
        AnchorPane.setLeftAnchor(arrival_scroll, 300.0); // TODO: diff
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

            abort_arrival();
        }
    }

    private void abort_arrival() {
        if (arrival_scroll != null) {
            ROOT.getChildren().remove(arrival_scroll);
        }
    }
    // </editor-fold>

    // <editor-fold desc="#on departure date">
    private void onclick_departure_datetime(ControlEvent e) {
        System.out.println("departure datetime datePicker clicked");
        controller.catchEvent(e); // Broadcasting
    }

    /**
     * WBS: View1 - P1 - SDT_B
     * WBS: View1 - P1 - SDT_1_B
     * onclick departure datetime datepicker
     * @param e departure datetime select
     */
    private void onselect_departure_datetime(ControlEvent e) {
        System.out.println("departure datetime datePicker selected");
        controller.catchEvent(e); // Broadcasting

        // DatePicker에서 선택한 날짜를 가져옴
        LocalDate selectedDate = DEPARTURE_DATE.getValue();

        if (selectedDate == null) {
            System.out.println("No date selected");
            return;
        }

        _env.departure_date = selectedDate;
    }

    private void abort_departure_datetime() {
        DEPARTURE_DATE.getValue();
    }
    // </editor-fold>

    // <editor-fold desc="#on arrival date">
    private void onclick_arrival_datetime(ControlEvent e) {
        System.out.println("arrival datetime datePicker clicked");
        controller.catchEvent(e);
    }

    /**
     * WBS: View1 - P1 - END_B
     * WBS: View1 - P1 - END_1_B
     * onclick arrival datetime datePicker
     * @param e arrival datetime select
     */
    private void onselect_arrival_datetime(ControlEvent e) {
        System.out.println("arrival datetime datePicker selected");
        controller.catchEvent(e); // Broadcasting

        // DatePicker에서 선택한 날짜를 가져옴
        LocalDate selectedDate = ARRIVAL_DATE.getValue(); // TODO: diff

        if (selectedDate == null) {
            System.out.println("No date selected");
            return;
        }

        _env.arrival_date = selectedDate; // TODO: diff
    }

    private void abort_arrival_datetime() {
        ARRIVAL_DATE.getValue();
    }
    // </editor-fold>

    // <editor-fold desc="#on search">
    private void onclick_search(ControlEvent e) {
        System.out.println("Serach button clicked");
        controller.catchEvent(e); // Broadcasting

        // 로그인 되어있는지 확인
        if (_env.selected_user != null) {
            if (isCanNavigate()) {
                System.out.println("안내 조건을 만족했습니다. 다음 단계로 넘어갑니다.");
                controller.reservation.doReservation();
            } else {
                System.out.println("아직 선택하지 않은 조건이 있습니다.");
            }

            return;
        }

        if (requestLogin()) {
            System.out.println("로그인 진행");
            controller.titleTab.login_required();
        } else {
            System.out.println("로그인이 되어있지 않으므로 항공권 검색을 진행하지 않습니다.");
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


    private boolean requestLogin() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("로그인 필요");
        alert.setHeaderText("로그인이 되어있지 않습니다.");
        alert.setContentText("로그인을 하시겠습니까?");

        // 대화상자 표시하고 사용자의 응답을 기다림
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // 사용자가 확인 버튼을 눌렀을 때의 작업
            System.out.println("User chose OK");
            return true;
        } else {
            // 사용자가 취소 버튼을 눌렀을 때의 작업
            System.out.println("User chose Cancel or closed the dialog");
            return false;
        }
    }


    // </editor-fold>

}
