package app.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class V1_Controller {

    @FXML private Button HOME;
    @FXML private Button DEPARTURE;
    @FXML private Button ARRIVE;
    @FXML private Button DATE;
    @FXML private Button SEARCH;
    @FXML private Button FIND_ALL;


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
        System.out.println("All festivals button clicked");
    }

    /**
     * WBS: View1 - P1 - CAT_B
     * onclick category on festival button
     * @param event category button click
     */
    @FXML
    private void onclick_cat_festivals(ActionEvent event) {
        System.out.println("Cat festivals button clicked");
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
