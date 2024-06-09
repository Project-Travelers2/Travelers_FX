package app.fx.elements;

import app.fx.Data.USERS;
import app.fx.V1_Controller;
import app.fx._env;
import app.fx.elements._User_Pane.User_Customer;
import app.fx.elements._User_Pane.User_LoginRequired;
import app.fx.elements._User_Pane.User_Manager;
import app.fx.elements._User_Pane.User_Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Title_tab extends Pane {

    private final V1_Controller controller;

    @FXML
    private Pane titleTab;

    @FXML
    private Button HOME;

    @FXML
    private Label titleLabel;

    private User_Pane userPane;

    LoginPage loginPage;

    private void initialize() {
        // Initialize and add the background Rectangle
        Rectangle backgroundRect = new Rectangle(1600.0, 50.0);
        backgroundRect.setArcHeight(5.0);
        backgroundRect.setArcWidth(5.0);
        backgroundRect.setFill(Color.web("#8bc7ff"));
        backgroundRect.setStroke(Color.BLACK);
//        backgroundRect.setStrokeType(Rectangle.StrokeType.INSIDE);
        titleTab.getChildren().add(backgroundRect);

        // Initialize the inner Pane
        Pane innerPane = new Pane();
        innerPane.setLayoutX(40.0);
        innerPane.setLayoutY(-2.0);
        innerPane.setPrefHeight(50.0);
        innerPane.setPrefWidth(280.0);

        // Initialize and add the HOME Button
        HOME = new Button("HOME");
        HOME.setLayoutY(7.0);
        HOME.setPrefHeight(40.0);
        HOME.setPrefWidth(100.0);
        HOME.setMnemonicParsing(false);
        innerPane.getChildren().add(HOME);

        // Initialize and add the Separator
        Separator separator = new Separator();
        separator.setLayoutX(259.0);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(50.0);
        innerPane.getChildren().add(separator);

        // Initialize and add the Label
        titleLabel = new Label("여행상품");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(95.0);
        titleLabel.setLayoutY(2.0);
        titleLabel.setPrefHeight(51.0);
        titleLabel.setPrefWidth(165.0);
        titleLabel.setFont(new Font("D2Coding", 30.0));
        innerPane.getChildren().add(titleLabel);

        titleTab.getChildren().add(innerPane);
    }

    public Title_tab(Pane titleTab, V1_Controller controller, USERS currentUser) {
        this.titleTab = titleTab;
        this.controller = controller;
        initialize();
        addUserPane(currentUser);

        HOME.setOnAction(e -> onclick_home());

        // TODO: login action in titleTab
        userPane.getElement(2).setOnAction(event -> logAction(event));
    }

    private void addUserPane(USERS currentUser) {
        // 유저 레벨 0 (로그인 안됨)
        if (_env.selected_user == null || _env.selected_user.user_type == "0") {
            // 기본 뷰 할당
            userPane = new User_LoginRequired(_env.selected_user);
        } else if (_env.selected_user.user_type == "1") {
            userPane = new User_Customer(_env.selected_user);
        } else if (_env.selected_user.user_type == "2") {
            userPane = new User_Staff(_env.selected_user);
        } else if (_env.selected_user.user_type == "3") {
            userPane = new User_Manager(_env.selected_user);
        }

        userPane.setLayoutX(1250.0);
        userPane.setLayoutY(0.0);
        titleTab.getChildren().add(userPane);
    }

    private void logAction(ActionEvent event) {
        // System.out.println(((Button)event.getSource()).getId() );

        String btnID = ((Button)event.getSource()).getId();

        if (btnID.equals("login_required")) login_required(event);
        else if (btnID.equals("logout_customer")) logout_customer(event);
        else if (btnID.equals("logout_staff")) logout_staff(event);
        else if (btnID.equals("logout_manager")) logout_manager(event);
    }

    private void login_required(ActionEvent event) {
        System.out.println("0: 로그인 요청");

        loginPage = new LoginPage();
        loginPage.setId("LoginPage");
        loginPage.loginButton.setOnAction(this::login);
        loginPage.registerButton.setOnAction(this::register);
        controller.ROOT.getChildren().add(loginPage);

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
                controller.ROOT.getChildren().remove(loginPage);
                controller.ROOT.getChildren().remove(userPane);


                // initialize에 있던 userView 생성코드 재실행 (USERS 따라서 나와야 하는 UI는 다르다)
                // 유저 레벨 0 (로그인 안됨)
                if (_env.selected_user == null || _env.selected_user.user_type == "0") {
                    // 기본 뷰 할당
                    userPane = new User_LoginRequired(_env.selected_user);
                } else if (_env.selected_user.user_type == "1") {
                    userPane = new User_Customer(_env.selected_user);
                } else if (_env.selected_user.user_type == "2") {
                    userPane = new User_Staff(_env.selected_user);
                } else if (_env.selected_user.user_type == "3") {
                    userPane = new User_Manager(_env.selected_user);
                }

                userPane.setLayoutX(1250.0);
                userPane.setLayoutY(0.0);
                titleTab.getChildren().add(userPane);

                controller.menuTab.onclick_all_festivals();

                if (userPane == null) {
                    return;
                }

                userPane.getElement(2).setOnAction(_event -> logAction(_event));
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
        final String url = _env.getEnv("DATABASE_URL");
        final String id = _env.getEnv("DATABASE_ID");
        final String pw =_env.getEnv("DATABASE_PW");
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

    private void logout_customer(ActionEvent event) {
        System.out.println("logout_customer");

    }

    private void logout_staff(ActionEvent event) {
        System.out.println("logout_staff");

    }

    private void logout_manager(ActionEvent event) {
        System.out.println("logout_manager");
    }

    /**
     * WBS: View1 - P1 - HOME_B
     * onclick home event
     */
    private void onclick_home() {
        System.out.println("Home button clicked");
        controller.menuTab.onclick_all_festivals();
    }


}
