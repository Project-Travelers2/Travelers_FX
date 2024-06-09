package app.fx.HA;

import app.fx.Data.FESTIVALS;
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

public class Sample_PaginationController implements Initializable {

    @FXML private Pane FESTIVALS;
    @FXML private Pagination FESTIVALS_PAGINATION;
    @FXML private GridPane GRID_FESTIVALS;

    private static final int ITEMS_PER_PAGE = 10;
    private static final int TOTAL_ITEMS = 50; // 총 아이템 수

    public Festivals_tab festivals_area;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        FESTIVALS // 루트 패널 가지고 Festivals에 할당하기
        festivals_area = new Festivals_tab(FESTIVALS);

        // 구조 만들었으니 뭐해야함? 데이터 갖다줘야지
//        _env.festival_informations
        // 테스트 데이터
        List<app.fx.Data.FESTIVALS> festival_informations = Queries.instance.all_festival_list();

        festivals_area.setGridFestivals(festival_informations);


////        // pagination 할당
////        FESTIVALS_PAGINATION = new Pagination((TOTAL_ITEMS / ITEMS_PER_PAGE + 1), 0);
//        FESTIVALS_PAGINATION.setPageFactory(new Callback<Integer, Node>() {
//            @Override
////            public GridPane call(Integer pageIndex) {
////                return new GridPane();
//////                return createPage(pageIndex);
////            }
//            public VBox call(Integer pageIndex) {
//                return createPage(pageIndex);
//            }
//        });
//
//        FESTIVALS_PAGINATION.setPageCount(TOTAL_ITEMS / ITEMS_PER_PAGE);
    }

    private VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * ITEMS_PER_PAGE;
        for (int i = page; i < page + ITEMS_PER_PAGE && i < TOTAL_ITEMS; i++) {
            box.getChildren().add(new Label("Item " + (i + 1)));
        }
        return box;
    }
}
