package app.fx.elements;

import app.fx.Data.FESTIVALS;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Callback;

import java.util.List;

public class Festivals_tab {
    private Pane rootFestivals;
    private Pagination festivalsPagination;
    List<FESTIVALS> festivals;

    private final int DISPLAY_COUNT_PER_PAGE = 6;
    private final int ROWS_PER_PAGE = 3;
    private int maxPageCount = 1;


    public Festivals_tab() {
        init();
    }

    public Festivals_tab(Pane rootFestivals) {
        this();
        this.rootFestivals = rootFestivals;
        this.rootFestivals.getChildren().add(festivalsPagination);
    }

    /**
     * 객체 처음 시작할때 실행되는 코드
     * (패널 구조 짜는코드)
     */
    public void init() {
        initPagination();
    }

    /**
     * Pagination 초기화
     */
    public void initPagination() {
        festivalsPagination = new Pagination();
        festivalsPagination.setLayoutY(0.0);
        festivalsPagination.setPrefHeight(600.0);
        festivalsPagination.setPrefWidth(1600.0);
    }



    private GridPane initializeGrid() {
        GridPane newGridPane = new GridPane();

        newGridPane = new GridPane();
        newGridPane.setHgap(10.0);
        newGridPane.setVgap(10.0);
        newGridPane.setPrefHeight(540.0);
        newGridPane.setPrefWidth(1600.0);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col1.setMinWidth(10.0);
        col1.setPrefWidth(100.0);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col2.setMinWidth(10.0);
        col2.setPrefWidth(100.0);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        col3.setMinWidth(10.0);
        col3.setPrefWidth(100.0);

        newGridPane.getColumnConstraints().addAll(col1, col2, col3);

        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(10.0);
        row1.setPrefHeight(40.0);
        row1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        RowConstraints row2 = new RowConstraints();
        row2.setMinHeight(10.0);
        row2.setPrefHeight(40.0);
        row2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        newGridPane.getRowConstraints().addAll(row1, row2);

        newGridPane.setPadding(new Insets(15.0, 15.0, 15.0, 15.0));

        return newGridPane;
    }

    private void resetTabValues(int maxPageCount) {
        festivalsPagination.setPageCount(maxPageCount);
        festivalsPagination.setCurrentPageIndex(0);
    }
    


    /**
     * 데이터 리스트를 바탕으로 pagination 설정
     * @param festivals 데이터 리스트
     */
    public void setGridFestivals(List<FESTIVALS> festivals) {
        this.festivals = festivals;

        //다른 버튼 눌러서 재시작할때
        maxPageCount = festivals.size() / DISPLAY_COUNT_PER_PAGE; // 최대 페이지수: 요소 수 / 페이지당 표시 요소 수
        resetTabValues(maxPageCount);

        festivalsPagination.setPageFactory(new Callback<Integer, Node>() {
            // 한번은 실행됨
            @Override
            public Node call(Integer index) {
                return createPage(index);
            }
        });
    }

    private Node createPage(Integer index) {
        GridPane gridPane = initializeGrid();

        for (int i = 0; i < DISPLAY_COUNT_PER_PAGE; i++) {
            if (festivalsPagination.getCurrentPageIndex() * DISPLAY_COUNT_PER_PAGE + i >= maxPageCount * DISPLAY_COUNT_PER_PAGE) {
                break;
            }

            FESTIVALS info = this.festivals.get(festivalsPagination.getCurrentPageIndex() * DISPLAY_COUNT_PER_PAGE + i );
            Festival_item item = new Festival_item(info);

            gridPane.add(item, i%ROWS_PER_PAGE, i/ROWS_PER_PAGE);
        }

        return gridPane;
    }


    
    
    public void pageUp() {
        int currentIndex = festivalsPagination.getCurrentPageIndex();
        if (currentIndex > 0) {
            festivalsPagination.setCurrentPageIndex(currentIndex - 1);
        }
    }

    public void pageDown() {
        int currentIndex = festivalsPagination.getCurrentPageIndex();
        if (currentIndex < festivalsPagination.getPageCount() - 1) {
            festivalsPagination.setCurrentPageIndex(currentIndex + 1);
        }
    }


    /**
     * 현재 페이지 넘버를 반환
     * @return pageNumber
     */
    public int getPageNumber() {
        return festivalsPagination.getPageCount();
    }
}

