package app.fx.HA;

import app.fx.Control.Controller;
import app.fx.elements.Festivals_tab;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Sample_PaginationController extends Controller implements Initializable {

    @FXML private Pane FESTIVALS;

    public Festivals_tab festivals_area;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //FESTIVALS // 루트 패널 가지고 Festivals에 할당하기
        festivals_area = new Festivals_tab(FESTIVALS);

        // 테스트 데이터
        List<app.fx.Data.FESTIVALS> festival_informations = Queries.instance.all_festival_list();

        festivals_area.setGridFestivals(festival_informations, this);
    }
}
