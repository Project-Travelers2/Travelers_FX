package app.fx.HA;

import app.fx.Controllers.Controller;
import app.fx.Data.FESTIVALS;
import app.fx._env;
import app.fx.elements.DetailedImageView;
import app.fx.elements.Festival_item;
import app.fx.elements.Festivals_tab;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
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
