package app.fx;

import app.fx.Control.ControlEvent;
import app.fx.Control.Controller;
import app.fx.elements.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller_V2 extends Controller implements Initializable {

    @FXML public AnchorPane ROOT;

    @FXML private Pane TITLE_TAB;
    public Title_tab titleTab;

    @FXML public Pane FLIGHT_TAB;
    public FlightTab flightTab;

    @FXML public Pane MENU_TAB;
    public MenuTab menuTab;

    @FXML public Pane CONTENTS;
    public Festivals_tab festivalsTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize Tab
        titleTab = new Title_tab(ROOT, this);
        flightTab = new FlightTab(ROOT, this);
        menuTab = new MenuTab(ROOT, this);

        menuTab.onclick_all_festivals();

//        scene.getRoot().applyCss();
    }


    //============================================================================

    public void catchEvent(ControlEvent e) {
        switch (e.getEventCode()) {
            case FLIGHT_DEPARTURE -> System.out.println("event; DEPARTURE");
            case FLIGHT_ARRIVAL -> System.out.println("event; ARRIVAL");
            case FLIGHT_DEPARTURE_DATE -> System.out.println("event; DEPARTURE_DATE");
            case FLIGHT_ARRIVAL_DATE -> System.out.println("event; ARRIVAL_DATE");
            case FLIGHT_SEARCH -> System.out.println("event; SEARCH");
        }
    }


    // <editor-fold defaultstate="collapsed" desc="region: GridPane Control">

    public void handleKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case ENTER -> enter();
            case A -> pageDown();
            case D -> pageUp();
        }
    }

    private void enter() {
        if (titleTab.loginPage != null && titleTab.signupPage == null) {
            System.out.println("로그인 이벤트 사용가능");
            titleTab.keyRequest("login");
        } else {
            System.out.println("로그인 이벤트 불가");
        }
    }

    /**
     * GridPane 페이지 다음 페이지 이동
     */
    public void pageUp() {
        try {
            festivalsTab.keyRequest("up");
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
            festivalsTab.keyRequest("down");
        } catch (IndexOutOfBoundsException e) {
            // 추가작업 수행하지 않음
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // </editor-fold>
}
