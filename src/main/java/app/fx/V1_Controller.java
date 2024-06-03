package app.fx;

import app.fx.Data.AIRPORT_INFORMATION;
import app.fx.Data.FESTIVAL_INFORMATION;
import app.fx.HA.Queries;
import app.fx.elements.Festival_item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.List;

public class V1_Controller {

    @FXML private AnchorPane ROOT;
    @FXML private Button HOME;
    @FXML private Button DEPARTURE;
    @FXML private Button ARRIVAL;
    @FXML private DatePicker DEPARTURE_DATE;
    @FXML private DatePicker ARRIVAL_DATE;
    @FXML private Button SEARCH;
    @FXML private Button FIND_ALL;
    @FXML private GridPane GRID_FESTIVALS;


    /**
     * WBS: View1 - P1 - HOME_B
     * onclick home event
     * @param event home button click
     */
    @FXML
    private void onclick_home(ActionEvent event) {
        System.out.println("Home button clicked");
        onclick_all_festivals(event);
    }

    /**
     * WBS: View1 - P1 - ALL_B
     * onclick find all festivals button
     * @param event all button click
     */
    @FXML
    private void onclick_all_festivals(ActionEvent event) {
        // 페이지 속성 초기화
        _env.ResetItemProperties();

        // 데이터 리스트 받아오기
        System.out.println("All festivals button clicked");
        _env.festival_informations = Queries.instance.all_festival_list();

        display();

        // TEST: 리스트에서 현재 보여줘야할 데이터의 뷰 위치 확인
        //for (FESTIVAL_INFORMATION fest_info1 : fest_info) {
        //    System.out.println(fest_info1.toString());
        //}
    }

    /**
     * WBS: View1 - P1 - CAT_B
     * onclick category on festival button
     * @param event category button click
     */
    @FXML
    private void onclick_cat_festivals(ActionEvent event) {
        // 클릭한 메뉴 버튼을 확인합니다.
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();

        // 페이지 속성 초기화
        _env.ResetItemProperties();

        // 축제 코드를 생성합니다.
        int festival_code = 0;
        switch (buttonId) {
            case "FIND_MOVIE":  festival_code = 1;  break;
            case "FIND_MUSIC":  festival_code = 2;  break;
            case "FIND_FASSION":festival_code = 3;  break;
            case "FIND_FOOD":   festival_code = 4;  break;
            case "FIND_SUMMER": festival_code = 5;  break;
            case "FIND_WINTER": festival_code = 6;  break;
            case "FIND":        festival_code = 7; // TODO 7번 데이터 분류 질문
                break;
            default:    break;
        }

        // 데이터 리스트 받아오기
        _env.festival_informations = Queries.instance.specific_festival_list(festival_code);

        display();

        // TEST: 리스트에서 현재 보여줘야할 데이터 리스트 보기
        //for (FESTIVAL_INFORMATION fest_info1 : fest_info) {
        //    System.out.println(fest_info1.toString());
        //}
    }


    /**
     * WBS: View1 - P1 - DEP_B
     * onclick departure location button
     * @param event departure button click
     */
    @FXML
    private void onclick_departure(ActionEvent event) {
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


    /**
     * WBS: View1 - P1 - ARR_B
     * onclick arrival location button
     * @param event arrival button click
     */
    @FXML
    private void onclick_arrival(ActionEvent event) {
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
    @FXML
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
     * @param event departure datetime select
     */
    @FXML
    private void onclick_departure_datetime(ActionEvent event) {
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
    @FXML
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

    /**
     * WBS: View1 - P1 - SRC_B
     * onclick search button
     * @param event search button click
     */
    @FXML
    private void onclick_search(ActionEvent event) {
        System.out.println("Serach button clicked");
    }

    //============================================================================

    /**
     * @deprecated 이 메서드는 ALL_B, CAT_B에서 구현되었습니다. 때문에 구현을 중단합니다.
     * {@link #onclick_all_festivals(ActionEvent)} instead
     * {@link #onclick_cat_festivals(ActionEvent)} instead
     * WBS: View1 - P2 - ITM_B
     * initialize travelers festivals
     */
    @FXML
    private void initialize_travelers_festivals() {
        System.out.println("initialize_travelers_festivals");
    }

    /**
     * WBS: View1 - P2 - ITM_B2
     * onclick festival item button
     * @param event festival item click
     */
    @FXML
    private void onclick_festival_item(ActionEvent event) {
        System.out.println("Festival item button clicked");
    }

    /**
     * WBS: View1 - P2 - DET_B
     * onclick detail of festival button
     * @param event detail of festival click
     */
    @FXML
    private void onclick_detail_of_festival(ActionEvent event) {
        System.out.println("Detail of festival button clicked");
    }

    /**
     * WBS: View1 - P2 - RES_B
     * onclick navigate flight page
     * @param event navigate flight page click
     */
    @FXML
    private void onclick_navigate_flight(ActionEvent event) {
        System.out.println("Navigate flight button clicked");
    }


    // <editor-fold defaultstate="collapsed" desc="region: GridPane Control">
    /**
     * GridPane 페이지 다음 페이지 이동
     */
    public void pageUp() {
        try {
            _env.pageUp();
            display();
        } catch (IndexOutOfBoundsException e) {
            // 추가작업 수행하지 않음
        } catch  (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GridPane 페이지 이전 페이지 이동
     */
    public void pageDown() {
        try {
            _env.pageDown();
            display();
        } catch (IndexOutOfBoundsException e) {
            // 추가작업 수행하지 않음
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GridPane 비우기
     */
    private void gridClear() {
        // GirdPane에 있던 버튼들 제거
        List<Node> nodes = GRID_FESTIVALS.getChildren();
        nodes.clear();
    }

    /**
     * GridPane에 아이템 게시
     */
    private void display() {
        gridClear();

        int pageNum = _env.pageNumber;
        for (int i = 0; i < 6; i++) {
            // 최대 인덱스 초과시 종료
            if ((pageNum - 1) * 6 + i >= _env.festival_informations.size())
                break;

            // 6개만 출력
            System.out.println(_env.festival_informations.get(i).toString());
            FESTIVAL_INFORMATION info = _env.festival_informations.get( (pageNum - 1) * 6 + i); // (pageNum - 1) * 6 + i 번째 요소
            Festival_item item = new Festival_item(info);

            GRID_FESTIVALS.add(item, i%3, i/3);
        }
    }
    // </editor-fold>
}
