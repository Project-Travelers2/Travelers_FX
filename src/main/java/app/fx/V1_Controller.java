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

    @FXML private Pane TITLE_TAB;
    public Title_tab titleTab;
    @FXML private Button HOME;

    @FXML private Pane FLIGHT_TAB;
    public FlightTab flightTab;

    @FXML private Pane MENU_TAB;
    public MenuTab menuTab;

    @FXML public Pane CONTENTS;
    public Festivals_tab festivalsTab;


    @FXML private Button login;


    User_Pane pane;
    LoginPage loginPage;

    //database
    private static final String url = _env.getEnv("DATABASE_URL");
    private static final String id = _env.getEnv("DATABASE_ID");
    private static final String pw =_env.getEnv("DATABASE_PW");

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
            TITLE_TAB.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "1") {
            pane = new User_Customer(_env.selected_user);
            TITLE_TAB.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "2") {
            pane = new User_Staff(_env.selected_user);
            TITLE_TAB.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "3") {
            pane = new User_Manager(_env.selected_user);
            ROOT.getChildren().add(pane);
        }


        if (pane == null) {
            return;
        }

        // Initialize Tab
        titleTab = new Title_tab(ROOT, this);
        flightTab = new FlightTab(ROOT, FLIGHT_TAB);
        menuTab = new MenuTab(ROOT, MENU_TAB, this);

        menuTab.onclick_all_festivals();

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
                    TITLE_TAB.getChildren().add(pane);
                } else if (_env.selected_user.user_type == "1") {
                    pane = new User_Customer(_env.selected_user);
                    TITLE_TAB.getChildren().add(pane);
                } else if (_env.selected_user.user_type == "2") {
                    pane = new User_Staff(_env.selected_user);
                    TITLE_TAB.getChildren().add(pane);
                } else if (_env.selected_user.user_type == "3") {
                    pane = new User_Manager(_env.selected_user);
                    ROOT.getChildren().add(pane);
                }

                menuTab.onclick_all_festivals();

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
