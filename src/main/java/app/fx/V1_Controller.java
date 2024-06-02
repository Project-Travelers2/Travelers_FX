package app.fx;

import app.fx.Data.FESTIVAL_INFORMATION;
import app.fx.HA.Queries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.List;

public class V1_Controller {

    @FXML private Button HOME;
    @FXML private Button DEPARTURE;
    @FXML private Button ARRIVE;
    @FXML private Button DATE;
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
        System.out.println("Home button clicked111");
    }

    /**
     * WBS: View1 - P1 - ALL_B
     * onclick find all festivals button
     * @param event all button click
     */
    @FXML
    private void onclick_all_festivals(ActionEvent event) {
        // GirdPane에 있던 버튼들 제거
        List<Node> nodes = GRID_FESTIVALS.getChildren();
        nodes.clear();

        // 데이터 리스트 받아오기
        System.out.println("All festivals button clicked");
        List<FESTIVAL_INFORMATION> fest_info = Queries.instance.all_festival_list();
        
        // 리스트에서 현재 보여줘야할 데이터의 뷰 위치 확인
        for (FESTIVAL_INFORMATION fest_info1 : fest_info) {
            System.out.println(fest_info1.toString());
        }
        
        // 버튼 6개 생성
        Button addButton1 = new Button("Add New Button");
        addButton1.setPrefSize(515, 280);
        addButton1.setText("Festival name");
        addButton1.setFont(new Font("Arial", 24));
        addButton1.setId("FESTIVAL_ITEM");

        Button addButton2 = new Button("Add New Button");
        addButton2.setPrefSize(515, 280);
        addButton2.setText("Festival name");
        addButton2.setFont(new Font("Arial", 24));
        addButton2.setId("FESTIVAL_ITEM");

        Button addButton3 = new Button("Add New Button");
        addButton3.setPrefSize(515, 280);
        addButton3.setText("Festival name");
        addButton3.setFont(new Font("Arial", 24));
        addButton3.setId("FESTIVAL_ITEM");

        GRID_FESTIVALS.add(addButton1, 0, 0);
        GRID_FESTIVALS.add(addButton2, 1, 0);
        GRID_FESTIVALS.add(addButton3, 2, 0);
//        GridPane.setConstraints(addButton, 0, 0);

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
        //System.out.println(buttonId + " festivals button clicked");

        // GirdPane에 있던 버튼들 제거
        List<Node> nodes = GRID_FESTIVALS.getChildren();
        nodes.clear();

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

        List<FESTIVAL_INFORMATION> fest_info = Queries.instance.specific_festival_list(festival_code);

        for (FESTIVAL_INFORMATION fest_info1 : fest_info) {
            System.out.println(fest_info1.toString());
        }
    }

    /**
     * WBS: View1 - P1 - DEP_B
     * onclick departure location button
     * @param event departure button click
     */
    @FXML
    private void onclick_departure(ActionEvent event) {
        System.out.println("Departure button clicked");
    }

    /**
     * WBS: View1 - P1 - DEP_1_B
     * onclick select departure location button
     * @param event select departure location click
     */
    @FXML
    private void onclick_select_departure(ActionEvent event) {
        System.out.println("Select departure button clicked");
    }

    /**
     * WBS: View1 - P1 - ARR_B
     * onclick arrival location button
     * @param event arrival button click
     */
    @FXML
    private void onclick_arrival(ActionEvent event) {
        System.out.println("Arrival button clicked");
    }

    /**
     * WBS: View1 - P1 - ARR_1_B
     * onclick select arrival location button
     * @param event select arrival location button click
     */
    @FXML
    private void onclick_select_arrival(ActionEvent event) {
        System.out.println("Select arrival button clicked");
    }

    /**
     * WBS: View1 - P1 - SDT_B
     * onclick datetime button
     * @param event datetime button click
     */
    @FXML
    private void onclick_datetime(ActionEvent event) {
        System.out.println("Datetime button clicked");
    }

    /**
     * WBS: View1 - P1 - SDT_1_B
     * onclick select datetime button
     * @param event select datetime button click
     */
    @FXML
    private void onclick_select_datetime_button(ActionEvent event) {
        System.out.println("Select datetime button clicked");
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
}
