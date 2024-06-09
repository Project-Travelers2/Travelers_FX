package app.fx.elements;

import app.fx.HA.Queries;
import app.fx.V1_Controller;
import app.fx._env;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class MenuTab {

    private AnchorPane ROOT;
    private V1_Controller controller;

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
        backgroundRect.setStroke(Color.BLACK);
//        backgroundRect.setStrokeType(Rectangle.StrokeType.INSIDE);
        MenuBar.getChildren().add(backgroundRect);

        // Initialize ButtonBar
        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setButtonMinWidth(180.0);
        buttonBar.setButtonOrder("R_HE+U");
        buttonBar.setPrefHeight(100.0);
        buttonBar.setPrefWidth(1600.0);
        buttonBar.setPadding(new Insets(0, 50.0, 0, 20.0));

        // Initialize and add buttons to ButtonBar
        FIND_ALL = new Button("전체");
        FIND_ALL.setPrefHeight(70.0);
        FIND_ALL.setMnemonicParsing(false);
        buttonBar.getButtons().add(FIND_ALL);

        FIND_MOVIE = new Button("영화");
        FIND_MOVIE.setPrefHeight(70.0);
        FIND_MOVIE.setMnemonicParsing(false);
        buttonBar.getButtons().add(FIND_MOVIE);

        FIND_MUSIC = new Button("음악");
        FIND_MUSIC.setPrefHeight(70.0);
        FIND_MUSIC.setMnemonicParsing(false);
        buttonBar.getButtons().add(FIND_MUSIC);

        FIND_FASSION = new Button("패션");
        FIND_FASSION.setPrefHeight(70.0);
        FIND_FASSION.setMnemonicParsing(false);
        buttonBar.getButtons().add(FIND_FASSION);

        FIND_FOOD = new Button("음식");
        FIND_FOOD.setPrefHeight(70.0);
        FIND_FOOD.setMnemonicParsing(false);
        buttonBar.getButtons().add(FIND_FOOD);

        FIND_SUMMER = new Button("여름");
        FIND_SUMMER.setPrefHeight(70.0);
        FIND_SUMMER.setMnemonicParsing(false);
        buttonBar.getButtons().add(FIND_SUMMER);

        FIND_WINTER = new Button("겨울");
        FIND_WINTER.setPrefHeight(70.0);
        FIND_WINTER.setMnemonicParsing(false);
        buttonBar.getButtons().add(FIND_WINTER);

        FIND_ETC = new Button("기타");
        FIND_ETC.setPrefHeight(70.0);
        FIND_ETC.setMnemonicParsing(false);
        buttonBar.getButtons().add(FIND_ETC);

        MenuBar.getChildren().add(buttonBar);

        // Add padding to MenuBar
        MenuBar.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
    }

    public MenuTab(AnchorPane ROOT, Pane menuBar, V1_Controller controller) {
        this.ROOT = ROOT;
        this.MenuBar = menuBar;
        this.controller = controller;
        initialize();

        FIND_ALL.setOnAction(e -> onclick_all_festivals());
        FIND_MOVIE.setOnAction(e -> onclick_cat_festivals(1));
        FIND_MUSIC.setOnAction(e -> onclick_cat_festivals(2));
        FIND_FASSION.setOnAction(e -> onclick_cat_festivals(3));
        FIND_FOOD.setOnAction(e -> onclick_cat_festivals(4));
        FIND_SUMMER.setOnAction(e -> onclick_cat_festivals(5));
        FIND_WINTER.setOnAction(e -> onclick_cat_festivals(6));
        FIND_ETC.setOnAction(e -> onclick_cat_festivals(7));
    }

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
        controller.festivalsTab = new Festivals_tab(controller.CONTENTS);
        controller.festivalsTab.setGridFestivals(_env.festival_informations, controller);
    }

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
        controller.festivalsTab = new Festivals_tab(controller.CONTENTS);
        controller.festivalsTab.setGridFestivals(_env.festival_informations, controller);
    }


}