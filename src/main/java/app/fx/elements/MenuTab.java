package app.fx.elements;

import app.fx.Control.ControlEvent;
import app.fx.Data.EventCode;
import app.fx.HA.Queries;
import app.fx.Controller_V2;
import app.fx._env;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.text.Font;

public class MenuTab {

    private AnchorPane ROOT;
    private Controller_V2 controller;

    @FXML
    public Pane MenuBar;

    @FXML
    public Button FIND_ALL;

    @FXML
    public Button FIND_MOVIE;

    @FXML
    public Button FIND_MUSIC;

    @FXML
    public Button FIND_FASSION;

    @FXML
    public Button FIND_FOOD;

    @FXML
    public Button FIND_SUMMER;

    @FXML
    public Button FIND_WINTER;

    @FXML
    public Button FIND_ETC;

    private void initialize() {
        // Initialize and add the background Rectangle
        Rectangle backgroundRect = new Rectangle(1600.0, 100.0);
        backgroundRect.setArcHeight(5.0);
        backgroundRect.setArcWidth(5.0);
        backgroundRect.setFill(Color.DODGERBLUE);
//        backgroundRect.setStroke(Color.BLACK);
//        backgroundRect.setStrokeType(Rectangle.StrokeType.INSIDE);
        MenuBar.getChildren().add(backgroundRect);

        // Initialize ButtonBar
        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setButtonMinWidth(180.0);
        buttonBar.setButtonOrder("R_HE+U");
        buttonBar.setPrefHeight(100.0);
        buttonBar.setPrefWidth(1600.0);
        buttonBar.setStyle("-fx-background-color: #89c3fb");
        buttonBar.setPadding(new Insets(0, 50.0, 0, 20.0));

        // Initialize and add buttons to ButtonBar
        FIND_ALL = new Button("전체");
        FIND_ALL.setPrefHeight(70.0);
        FIND_ALL.setMnemonicParsing(false);
        FIND_ALL.setFont(Font.font("배달의민족 도현",18));
        FIND_ALL.setTextFill(Paint.valueOf("white"));
        FIND_ALL.setStyle("-fx-background-color: #89c3fb");
        buttonBar.getButtons().add(FIND_ALL);

        FIND_MOVIE = new Button("영화");
        FIND_MOVIE.setPrefHeight(70.0);
        FIND_MOVIE.setMnemonicParsing(false);
        FIND_MOVIE.setFont(Font.font("배달의민족 도현",18));
        FIND_MOVIE.setTextFill(Paint.valueOf("white"));
        FIND_MOVIE.setStyle("-fx-background-color: #89c3fb");
        buttonBar.getButtons().add(FIND_MOVIE);

        FIND_MUSIC = new Button("음악");
        FIND_MUSIC.setPrefHeight(70.0);
        FIND_MUSIC.setMnemonicParsing(false);
        FIND_MUSIC.setFont(Font.font("배달의민족 도현",18));
        FIND_MUSIC.setTextFill(Paint.valueOf("white"));
        FIND_MUSIC.setStyle("-fx-background-color: #89c3fb");
        buttonBar.getButtons().add(FIND_MUSIC);

        FIND_FASSION = new Button("패션");
        FIND_FASSION.setPrefHeight(70.0);
        FIND_FASSION.setMnemonicParsing(false);
        FIND_FASSION.setFont(Font.font("배달의민족 도현", 18));
        FIND_FASSION.setTextFill(Paint.valueOf("white"));
        FIND_FASSION.setStyle("-fx-background-color: #89c3fb");
        buttonBar.getButtons().add(FIND_FASSION);

        FIND_FOOD = new Button("음식");
        FIND_FOOD.setPrefHeight(70.0);
        FIND_FOOD.setMnemonicParsing(false);
        FIND_FOOD.setFont(Font.font("배달의민족 도현",18));
        FIND_FOOD.setTextFill(Paint.valueOf("white"));
        FIND_FOOD.setStyle("-fx-background-color: #89c3fb");
        buttonBar.getButtons().add(FIND_FOOD);

        FIND_SUMMER = new Button("여름");
        FIND_SUMMER.setPrefHeight(70.0);
        FIND_SUMMER.setMnemonicParsing(false);
        FIND_SUMMER.setFont(Font.font("배달의민족 도현",18));
        FIND_SUMMER.setTextFill(Paint.valueOf("white"));
        FIND_SUMMER.setStyle("-fx-background-color: #89c3fb");
        buttonBar.getButtons().add(FIND_SUMMER);

        FIND_WINTER = new Button("겨울");
        FIND_WINTER.setPrefHeight(70.0);
        FIND_WINTER.setMnemonicParsing(false);
        FIND_WINTER.setFont(Font.font("배달의민족 도현",18));
        FIND_WINTER.setTextFill(Paint.valueOf("white"));
        FIND_WINTER.setStyle("-fx-background-color: #89c3fb");
        buttonBar.getButtons().add(FIND_WINTER);

        FIND_ETC = new Button("기타");
        FIND_ETC.setPrefHeight(70.0);
        FIND_ETC.setMnemonicParsing(false);
        FIND_ETC.setFont(Font.font("배달의민족 도현",18));
        FIND_ETC.setTextFill(Paint.valueOf("white"));
        FIND_ETC.setStyle("-fx-background-color: #89c3fb");
        buttonBar.getButtons().add(FIND_ETC);

        MenuBar.getChildren().add(buttonBar);

        // Add padding to MenuBar
        MenuBar.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
    }

    public MenuTab(AnchorPane ROOT, Controller_V2 controller) {
        this.ROOT = ROOT;
        this.MenuBar = controller.MENU_TAB;
        this.controller = controller;
        initialize();

        FIND_ALL.setOnAction(e -> onclick_all_festivals(new ControlEvent(e, EventCode.MENU_ALL)));
        FIND_MOVIE.setOnAction(e -> onclick_cat_festivals(1, new ControlEvent(e, EventCode.MENU_CATEGORY)));
        FIND_MUSIC.setOnAction(e -> onclick_cat_festivals(2, new ControlEvent(e, EventCode.MENU_CATEGORY)));
        FIND_FASSION.setOnAction(e -> onclick_cat_festivals(3, new ControlEvent(e, EventCode.MENU_CATEGORY)));
        FIND_FOOD.setOnAction(e -> onclick_cat_festivals(4, new ControlEvent(e, EventCode.MENU_CATEGORY)));
        FIND_SUMMER.setOnAction(e -> onclick_cat_festivals(5, new ControlEvent(e, EventCode.MENU_CATEGORY)));
        FIND_WINTER.setOnAction(e -> onclick_cat_festivals(6, new ControlEvent(e, EventCode.MENU_CATEGORY)));
        FIND_ETC.setOnAction(e -> onclick_cat_festivals(7, new ControlEvent(e, EventCode.MENU_CATEGORY)));
    }

    public void receive(ControlEvent e) {
        // 여긴 딱히 건드릴게 없음.
    }

    // <editor-fold desc="#all festivals">
    /**
     * WBS: View1 - P1 - ALL_B
     * onclick find all festivals button
     */
    @FXML
    public void onclick_all_festivals() {
        // 페이지 속성 초기화
        _env.ResetItemProperties();

        // 데이터 리스트 받아오기
        System.out.println("All festivals button clicked");


        _env.festival_informations = Queries.instance.all_festival_list();

        // CONTENTS 영역에 패널 생성
        if (controller.festivalsTab != null) {
            controller.festivalsTab.clear();
            controller.festivalsTab = null;
        }
        controller.festivalsTab = new Festivals_tab(controller.CONTENTS, controller);
        controller.festivalsTab.setGridFestivals(_env.festival_informations, controller);
    }

    public void onclick_all_festivals(ControlEvent e) {
        controller.catchEvent(e);
        onclick_all_festivals();
    }
    // </editor-fold>

    // <editor-fold desc="#category festivals">
    private void onclick_cat_festivals(int index) {

        // 페이지 속성 초기화
        _env.ResetItemProperties();

        // 축제 코드를 생성합니다.
        int festival_code = index;

        // 데이터 리스트 받아오기
        _env.festival_informations = Queries.instance.specific_festival_list(festival_code);

        // CONTENTS 영역에 패널 생성
        if (controller.festivalsTab != null) {
            controller.festivalsTab.clear();
            controller.festivalsTab = null;
        }
        controller.festivalsTab = new Festivals_tab(controller.CONTENTS, controller);
        controller.festivalsTab.setGridFestivals(_env.festival_informations, controller);
    }

    private void onclick_cat_festivals(int index, ControlEvent e) {
        controller.catchEvent(e);
        onclick_cat_festivals(index);
    }
    // </editor-fold>

}