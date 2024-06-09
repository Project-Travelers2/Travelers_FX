package app.fx;

import app.fx.Controllers.Controller;
import app.fx.Data.AIRPORT_INFORMATION;
import app.fx.Data.USERS;
import app.fx.HA.Queries;
import app.fx.elements.*;
import app.fx.elements._User_Pane.User_Customer;
import app.fx.elements._User_Pane.User_LoginRequired;
import app.fx.elements._User_Pane.User_Manager;
import app.fx.elements._User_Pane.User_Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class V1_Controller extends Controller implements Initializable {

    @FXML private AnchorPane ROOT;
    @FXML private Pane TitleBar;
    @FXML private Pane CONTENTS;
    private Festivals_tab festivalsTab;

    @FXML private Pane FLIGHT_TAB;
    public FlightTab flightTab;

    @FXML private Button HOME;

//    @FXML private Button DEPARTURE;
//    @FXML private Button ARRIVAL;
//    @FXML private DatePicker DEPARTURE_DATE;
//    @FXML private DatePicker ARRIVAL_DATE;
//    @FXML private Button SEARCH;

    @FXML private Button FIND_ALL;
//    @FXML private GridPane GRID_FESTIVALS;
    @FXML private Button login;

//    public Scene curr_scene;

    User_Pane pane;
    LoginPage loginPage;
    //database
    private static final String url = _env.getEnv("DATABASE_URL");
    private static final String id = _env.getEnv("DATABASE_ID");
    private static final String pw =_env.getEnv("DATABASE_PW");

//    public void reapplyCssToScene() {
//        curr_scene.getRoot().applyCss();
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        login.setOnAction(event -> login());
//        System.out.println("로그인 창으로 이동합니다.");

        USERS userTest = new USERS();
        userTest.user_type = "0";
        _env.selected_user = userTest;

        User_Pane pane = null;
        // 유저 레벨 0 (로그인 안됨)
        if (_env.selected_user == null || _env.selected_user.user_type == "0") {
            // 기본 뷰 할당
            pane = new User_LoginRequired(_env.selected_user);
            TitleBar.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "1") {
            pane = new User_Customer(_env.selected_user);
            TitleBar.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "2") {
            pane = new User_Staff(_env.selected_user);
            TitleBar.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "3") {
            pane = new User_Manager(_env.selected_user);
            ROOT.getChildren().add(pane);
        }

        onclick_all_festivals(null);

        if (pane == null) {
            return;
        }

        // Initialize FlightTab with the FLIGHT_TAB Pane
        flightTab = new FlightTab(ROOT, FLIGHT_TAB);

        // TODO: 11111ASD
//        // Bind event handlers
//        flightTab.DEPARTURE.setOnAction(e -> onclick_departure(e));
//        flightTab.ARRIVAL.setOnAction(e -> onclick_arrival(e));
//        flightTab.DEPARTURE_DATE.setOnAction(e -> onclick_departure_datetime(e));
//        flightTab.ARRIVAL_DATE.setOnAction(e -> onclick_arrival_datetime(e));
//        flightTab.SEARCH.setOnAction(e -> onclick_search(e));



//        Scene scene = ROOT.getScene();
//        scene.getRoot().applyCss();

        pane.getElement(2).setOnAction(event -> logAction(event));
    }

    private void logAction(ActionEvent event) {
        // System.out.println(((Button)event.getSource()).getId() );

        String btnID = ((Button)event.getSource()).getId();

        if (btnID.equals("login_required")) login_required(event);
        else if (btnID.equals("logout_customer")) logout_customer(event);
        else if (btnID.equals("logout_staff")) logout_staff(event);
        else if (btnID.equals("logout_manager")) logout_manager(event);
    }

    // TODO: 0607 로그인
    private void login_required(ActionEvent event) {
        System.out.println("0: 로그인 요청");

        loginPage = new LoginPage();
        loginPage.setId("LoginPage");
        loginPage.loginButton.setOnAction(this::login);
        loginPage.registerButton.setOnAction(this::register);
        ROOT.getChildren().add(loginPage);

        // 로그인이 완료되었다면
        // _env.selectedUser에 받아온 유저 정보를 할당
        // 이 정보는 user_type이 1,2,3중에 하나임

        // userView 제거





    }

    private void login(ActionEvent event) {
        String userId = loginPage.getUsername();
        String password = loginPage.getPassword();
        if (validateLogin(userId, password)) {
            // 화면전환
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setContentText("로그인 되었습니다");
                alert.show();

                // TODO: id로 들어가서 로그인 창, 유저 뷰 제거하기
                ROOT.getChildren().remove(loginPage);
                ROOT.getChildren().remove(pane);


                // initialize에 있던 userView 생성코드 재실행 (USERS 따라서 나와야 하는 UI는 다르다)
                pane = null;
                // 유저 레벨 0 (로그인 안됨)
                if (_env.selected_user == null || _env.selected_user.user_type == "0") {
                    // 기본 뷰 할당
                    pane = new User_LoginRequired(_env.selected_user);
                    TitleBar.getChildren().add(pane);
                } else if (_env.selected_user.user_type == "1") {
                    pane = new User_Customer(_env.selected_user);
                    TitleBar.getChildren().add(pane);
                } else if (_env.selected_user.user_type == "2") {
                    pane = new User_Staff(_env.selected_user);
                    TitleBar.getChildren().add(pane);
                } else if (_env.selected_user.user_type == "3") {
                    pane = new User_Manager(_env.selected_user);
                    ROOT.getChildren().add(pane);
                }

                onclick_all_festivals(null);

                if (pane == null) {
                    return;
                }

                pane.getElement(2).setOnAction(_event -> logAction(_event));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("경고");
            alert.setContentText("로그인 실패");
            alert.show();
            loginPage.username.clear();
            loginPage.password.clear();
            System.out.println("로그인 실패");
        }
        System.out.println("id: " + loginPage.getUsername());
        System.out.println("pw: " + loginPage.getPassword());
    }

    private boolean validateLogin(String userId, String userpw) {
        boolean isValid = false;
        try (Connection conn = DriverManager.getConnection(url, id, pw)) {
            String query = "SELECT * FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userpw);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                USERS user = new USERS();
                user.user_id = resultSet.getInt("USER_ID");
                user.user_name = resultSet.getString("USER_NAME");
                user.user_password = resultSet.getString("USER_PASSWORD");
                user.user_type = String.valueOf(resultSet.getInt("USER_TYPE"));

                _env.selected_user = user;
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    private void register(ActionEvent event) {
        System.out.println("회원가입을 진행합니다.");
        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/Transport/signUp.fxml"));
//            Scene scene = new Scene(loader.load(), 600, 400);
//            // Assuming you are getting the Stage from the RootPane
//            Stage stage = (Stage) LoginPage.registerButton.getScene().getWindow();
//            stage.setScene(scene);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: 0607 로그인
    private void logout_customer(ActionEvent event) {
        System.out.println("1: 사용자 로그아웃");

        // user_type이 1
    }

    // TODO: 0607 로그인
    private void logout_staff(ActionEvent event) {
        System.out.println("2: 직원 로그아웃");

        // user_type이 2
    }

    // TODO: 0607 로그인
    private void logout_manager(ActionEvent event) {
        System.out.println("3: 관리자 로그아웃");

        // user_type이 3
    }

    /**
     * WBS: View1 - P1 - HOME_B
     * onclick home event
     * @param event home button click
     */
    @FXML
    private void onclick_home(ActionEvent event) {
        System.out.println("Home button clicked");
        onclick_all_festivals(event);
    }


    /**
     * 24.06.05
     * 작업자 : 형주희
     * 작업내용 : 메인 화면 로그인 창 이동
     */
    private void login() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/Transport/login_test.fxml"));
            Scene scene = new Scene(loader.load(), 1600, 900);
            // Assuming you are getting the Stage from the RootPane
            Stage stage = (Stage) login.getScene().getWindow();
            stage.setScene(scene);


            System.out.println("로그인 창으로 이동합니다");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * WBS: View1 - P1 - ALL_B
     * onclick find all festivals button
     * @param event all button click
     */
    @FXML
    private void onclick_all_festivals(ActionEvent event) {
        // 페이지 속성 초기화
        _env.ResetItemProperties();

        // 데이터 리스트 받아오기
        System.out.println("All festivals button clicked");
        _env.festival_informations = Queries.instance.all_festival_list();

        // CONTENTS 영역에 패널 생성
        if (festivalsTab != null) {
            festivalsTab.clear();
            festivalsTab = null;
        }
        festivalsTab = new Festivals_tab(CONTENTS);
        festivalsTab.setGridFestivals(_env.festival_informations, this);
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

        // 페이지 속성 초기화
        _env.ResetItemProperties();

        // 축제 코드를 생성합니다.
        int festival_code = 0;
        switch (buttonId) {
            case "FIND_MOVIE":  festival_code = 1;  break;
            case "FIND_MUSIC":  festival_code = 2;  break;
            case "FIND_FASSION":festival_code = 3;  break;
            case "FIND_FOOD":   festival_code = 4;  break;
            case "FIND_SUMMER": festival_code = 5;  break;
            case "FIND_WINTER": festival_code = 6;  break;
            case "FIND_ETC":    festival_code = 7; break;
            default:    break;
        }

        // 데이터 리스트 받아오기
        _env.festival_informations = Queries.instance.specific_festival_list(festival_code);

        // CONTENTS 영역에 패널 생성
        if (festivalsTab != null) {
            festivalsTab.clear();
            festivalsTab = null;
        }
        festivalsTab = new Festivals_tab(CONTENTS);
        festivalsTab.setGridFestivals(_env.festival_informations, this);
    }

    //============================================================================

    /**
     * @deprecated 이 메서드는 ALL_B, CAT_B에서 구현되었습니다. 때문에 구현을 중단합니다.
     * {@link #onclick_all_festivals(ActionEvent)} instead
     * {@link #onclick_cat_festivals(ActionEvent)} instead
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

    /**
     * GridPane 비우기
     */
    private void gridClear() {
        // GirdPane에 있던 버튼들 제거
//        List<Node> nodes = GRID_FESTIVALS.getChildren();
//        nodes.clear();
    }

//    /**
//     * GridPane에 아이템 게시
//     */
//    private void display() {
//        gridClear();
//
//        int pageNum = _env.pageNumber;
//        for (int i = 0; i < 6; i++) {
//            // 최대 인덱스 초과시 종료
//            if ((pageNum - 1) * 6 + i >= _env.festival_informations.size())
//                break;
//
//            // 6개만 출력
//            System.out.println(_env.festival_informations.get(i).toString());
//            FESTIVALS info = _env.festival_informations.get( (pageNum - 1) * 6 + i); // (pageNum - 1) * 6 + i 번째 요소
//            Festival_item item = new Festival_item(info); // Button을 상속한 Festival_item 인스턴스 생성
//            item.description.setOnMouseClicked(mouseEvent -> onclick_festival_item(item));
//            item.reserve.setOnAction(event -> onclick_festival_item(item));
//
////            GRID_FESTIVALS.add(item, i%3, i/3);
//        }
//    }


    // </editor-fold>
}
