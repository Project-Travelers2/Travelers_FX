package app.fx;

import app.fx.Controllers.Controller;
import app.fx.elements.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class V1_Controller extends Controller implements Initializable {

    @FXML public AnchorPane ROOT;

    @FXML private Pane TITLE_TAB;
    public Title_tab titleTab;

    @FXML private Pane FLIGHT_TAB;
    public FlightTab flightTab;

    @FXML private Pane MENU_TAB;
    public MenuTab menuTab;

    @FXML public Pane CONTENTS;
    public Festivals_tab festivalsTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize Tab
        titleTab = new Title_tab(ROOT, this, null);
        flightTab = new FlightTab(ROOT, FLIGHT_TAB);
        menuTab = new MenuTab(ROOT, MENU_TAB, this);

        menuTab.onclick_all_festivals();

//        scene.getRoot().applyCss();
    }


    //============================================================================

    /**
     * WBS: View1 - P2 - ITM_B2
     * onclick festival item button
     * 이 이벤트는 버튼 생성시 람다식으로 할당되었습니다.
     * @param item festival item click
     */
    @Override
    public void onclick_festival_item(Festival_item item) {
        System.out.println("Festival item button clicked");

        // 선택한 아이템을 할당합니다.
        _env.selected_festival = item;

        DetailedImageView detail = new DetailedImageView(item);
        ROOT.getChildren().add(detail);
    }


    // <editor-fold defaultstate="collapsed" desc="region: GridPane Control">
    /**
     * GridPane 페이지 다음 페이지 이동
     */
    public void pageUp() {
        try {
            _env.pageUp();
//            display();
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
//            display();
        } catch (IndexOutOfBoundsException e) {
            // 추가작업 수행하지 않음
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // </editor-fold>
}
