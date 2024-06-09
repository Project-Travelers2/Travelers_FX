package app.fx.elements;

import app.fx.Data.USERS;
import app.fx.HA.Queries;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
    SignupPage signupPage;

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
        addUserPane();

        HOME.setOnAction(e -> onclick_home());

        // TODO: login action in titleTab
        userPane.getElement(2).setOnAction(event -> logAction(event));
    }



    // TODO: 0610 완료 후 리팩토링
    private void addUserPane() {
        // 유저 레벨 0 (로그인 안됨)
        if (_env.selected_user == null || _env.selected_user.user_type.equals("0")) {
            // 기본 뷰 할당
            System.out.println(0);
            userPane = new User_LoginRequired(_env.selected_user);
        } else if (_env.selected_user.user_type.equals("1")) {
            System.out.println(1);
            userPane = new User_Customer(_env.selected_user);
        } else if (_env.selected_user.user_type.equals("2")) {
            System.out.println(2);
            userPane = new User_Staff(_env.selected_user);
        } else if (_env.selected_user.user_type.equals("3")) {
            System.out.println(3);
            userPane = new User_Manager(_env.selected_user);
        }

        userPane.setLayoutX(1250.0);
        userPane.setLayoutY(0.0);
        titleTab.getChildren().add(userPane);
    }

    private void logAction(ActionEvent event) {
        String btnID = ((Button)event.getSource()).getId();

        if (btnID.equals("login_required")) login_required(event);
        else if (btnID.equals("logout_customer")) logout_customer(event);
        else if (btnID.equals("logout_staff")) logout_staff(event);
        else if (btnID.equals("logout_manager")) logout_manager(event);
    }

    private void login_required(ActionEvent event) {
        System.out.println("0: 로그인 요청");

        // 혹시 login버튼 두번 눌렀으면 로그인 버튼 제거
        if (loginPage != null) {
            titleTab.getChildren().remove(loginPage);
        }

        loginPage = new LoginPage();
        loginPage.setId("LoginPage");
        loginPage.loginButton.setOnAction(this::login);
        loginPage.registerButton.setOnAction(this::register);
        loginPage.exitPane.setOnMouseClicked(mouseEvent -> exitPane(mouseEvent));
        titleTab.getChildren().add(loginPage);
    }

    private void login(ActionEvent event) {
        String userId = loginPage.getUsername();
        String password = loginPage.getPassword();
        if (Queries.instance.validateLogin(userId, password)) {
            // 화면전환
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setContentText("로그인 되었습니다");
                alert.show();

                titleTab.getChildren().remove(loginPage);
                titleTab.getChildren().remove(signupPage);
                titleTab.getChildren().remove(userPane);

                addUserPane();

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

    private void register(ActionEvent event) {
        System.out.println("회원가입을 진행합니다.");
        try {
            // TODO: 0610 회원가입 페이지 코드화
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/Transport/signUp.fxml"));
//            Scene scene = new Scene(loader.load(), 600, 400);
//            // Assuming you are getting the Stage from the RootPane
//            Stage stage = (Stage) LoginPage.registerButton.getScene().getWindow();
//            stage.setScene(scene);
            signupPage = new SignupPage();
            signupPage.setId("SignupPage");
            // 이벤트 연결
//            loginPage.loginButton.setOnAction(this::login);
//            loginPage.registerButton.setOnAction(this::register);
            signupPage.exitPane.setOnMouseClicked(e -> exitPane(e));
            signupPage.registerButton.setOnAction(e -> register_request(e));
            titleTab.getChildren().add(signupPage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logout_customer(ActionEvent event) {
        System.out.println("logout_customer");
        logout();
    }

    private void logout_staff(ActionEvent event) {
        System.out.println("logout_staff");
        logout();
    }

    private void logout_manager(ActionEvent event) {
        System.out.println("logout_manager");
        logout();
    }


    private void logout() {
//        System.out.println("logout");
        // 1,2,3에서 동일한 상태로 로그인 요구 상태로 복귀
        _env.selected_user = null;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login");
        alert.setContentText("로그아웃 되었습니다");
        alert.show();

        titleTab.getChildren().remove(loginPage);
        titleTab.getChildren().remove(userPane);

        addUserPane();

        controller.menuTab.onclick_all_festivals();

        if (userPane == null) {
            return;
        }

        userPane.getElement(2).setOnAction(_event -> logAction(_event));

    }

    //===

    private void exitPane(MouseEvent event) {
        System.out.println("signup_exit");

        titleTab.getChildren().remove(loginPage);
        titleTab.getChildren().remove(signupPage);
        titleTab.getChildren().remove(userPane);

        addUserPane();

        controller.menuTab.onclick_all_festivals();

        if (userPane == null) {
            return;
        }

        userPane.getElement(2).setOnAction(_event -> logAction(_event));
    }

    private void register_request(ActionEvent event) {
        System.out.println("register_request");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("register_request");
        alert.setContentText("회원가입을 요청했습니다.");
        alert.show();


        // TODO: 0610 회원등록 요청

        
        // TODO: 되었다 가정하고 창 지우기
        titleTab.getChildren().remove(loginPage);
        titleTab.getChildren().remove(signupPage);
        titleTab.getChildren().remove(userPane);

        addUserPane();

        controller.menuTab.onclick_all_festivals();

        if (userPane == null) {
            return;
        }

        userPane.getElement(2).setOnAction(_event -> logAction(_event));
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
