package app.fx.elements;

import app.fx.Control.ControlEvent;
import app.fx.Data.EventCode;
import app.fx.HA.Queries;
import app.fx.Controller_V2;
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

public class Title_tab extends Pane {

    private final Controller_V2 controller;

    @FXML
    private Pane titleTab;

    @FXML
    private Button HOME;

    @FXML
    private Label titleLabel;

    private User_Pane userPane;

    public LoginPage loginPage;
    public SignupPage signupPage;

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

    public Title_tab(Pane titleTab, Controller_V2 controller) {
        this.titleTab = titleTab;
        this.controller = controller;
        initialize();
        addUserPane();

        HOME.setOnAction(e -> onclick_home(new ControlEvent(e, EventCode.TITLE_HOME)));

        // TODO: login action in titleTab
        userPane.getElement(2).setOnAction(e -> logAction(new ControlEvent(e, EventCode.TITLE_LOGACTION)) );
    }

    public void receive(ControlEvent e) {
        if (e.getEventCode() != EventCode.TITLE_LOGIN_REQUIRED &&
            e.getEventCode() != EventCode.TITLE_LOGIN &&
            e.getEventCode() != EventCode.TITLE_REGISTER &&
            e.getEventCode() != EventCode.TITLE_REGISTER_REQUEST &&
            e.getEventCode() != EventCode.TITLE_REGISTER_EXIT) {
            abort_login();
        }
    }

    public void keyRequest(String key) {
        switch (key.toString()) {
            case "login":
                login(new ControlEvent((ActionEvent) null, EventCode.TITLE_LOGIN));
        }
    }

    // <editor-fold desc="#home">
    /**
     * WBS: View1 - P1 - HOME_B
     * onclick home event
     */
    private void onclick_home(ControlEvent e) {
        System.out.println("Home button clicked");
        controller.menuTab.onclick_all_festivals();
    }
    // </editor-fold>

    private void addUserPane() {
        // 유저 레벨 0 (로그인 안됨)
        if (_env.selected_user == null || _env.selected_user.user_type.equals("0")) {
            // 기본 뷰 할당
            userPane = new User_LoginRequired(_env.selected_user);
        } else if (_env.selected_user.user_type.equals("1")) {
            userPane = new User_Customer(_env.selected_user);
        } else if (_env.selected_user.user_type.equals("2")) {
            userPane = new User_Staff(_env.selected_user);
        } else if (_env.selected_user.user_type.equals("3")) {
            userPane = new User_Manager(_env.selected_user);
        }

        userPane.setLayoutX(1250.0);
        userPane.setLayoutY(0.0);
        titleTab.getChildren().add(userPane);
    }

    // <editor-fold desc="#log action">
    private void logAction(ControlEvent e) {
        controller.catchEvent(e);
        String btnID = ((Button)e.event.getSource()).getId();

        if (btnID.equals("login_required")) login_required(new ControlEvent(e.event, EventCode.TITLE_LOGIN_REQUIRED));
        else if (btnID.equals("logout_customer")) logout_customer(new ControlEvent(e.event, EventCode.TITLE_LOGOUT_CUSTOMER));
        else if (btnID.equals("logout_staff")) logout_staff(new ControlEvent(e.event, EventCode.TITLE_LOGOUT_STAFF));
        else if (btnID.equals("logout_manager")) logout_manager(new ControlEvent(e.event, EventCode.TITLE_LOGOUT_MANAGER));
    }
    // </editor-fold>



    private void login_required(ControlEvent e) {
        controller.catchEvent(e);
        System.out.println("0: 로그인 요청");

        // 혹시 login버튼 두번 눌렀으면 로그인 버튼 제거
        if (loginPage != null) {
            titleTab.getChildren().remove(loginPage);
        }

        loginPage = new LoginPage();
        loginPage.setId("LoginPage");
        loginPage.loginButton.setOnAction(ev -> login(new ControlEvent(ev, EventCode.TITLE_LOGIN)) );
        loginPage.registerButton.setOnAction(ev -> register(new ControlEvent(ev, EventCode.TITLE_REGISTER)) );
        loginPage.exitPane.setOnMouseClicked(ev -> exitPane(new ControlEvent(ev, EventCode.TITLE_LOGIN_EXIT)) );
        titleTab.getChildren().add(loginPage);
    }

    private void login(ControlEvent e) {
        controller.catchEvent(e);
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

                userPane.getElement(2).setOnAction(ev -> logAction(new ControlEvent(ev, EventCode.TITLE_LOGIN)));
            } catch (Exception ex) {
                ex.printStackTrace();
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



    private void register(ControlEvent e) {
        controller.catchEvent(e);
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
            signupPage.exitPane.setOnMouseClicked(_e -> exitPane(new ControlEvent(_e, EventCode.TITLE_REGISTER_EXIT)));
            signupPage.registerButton.setOnAction(_e -> register_request(new ControlEvent(_e, EventCode.TITLE_REGISTER_REQUEST)));
            titleTab.getChildren().add(signupPage);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void logout_customer(ControlEvent e) {
        controller.catchEvent(e);
        System.out.println("logout_customer");
        logout();
    }

    private void logout_staff(ControlEvent e) {
        controller.catchEvent(e);
        System.out.println("logout_staff");
        logout();
    }

    private void logout_manager(ControlEvent e) {
        controller.catchEvent(e);
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

        userPane.getElement(2).setOnAction(e -> logAction(new ControlEvent(e, EventCode.TITLE_LOGIN)) );

    }

    //===

    private void abort_login() {
        if (loginPage != null) {
            titleTab.getChildren().remove(loginPage);
        }

        if (signupPage != null) {
            titleTab.getChildren().remove(signupPage);
        }
    }

    private void exitPane(ControlEvent e) {
        controller.catchEvent(e);
        System.out.println("signup_exit");

        titleTab.getChildren().remove(loginPage);
        titleTab.getChildren().remove(signupPage);
        titleTab.getChildren().remove(userPane);

        addUserPane();

        controller.menuTab.onclick_all_festivals();

        if (userPane == null) {
            return;
        }

        userPane.getElement(2).setOnAction(ev -> logAction(new ControlEvent(ev, EventCode.TITLE_LOGACTION)));
    }

    private void register_request(ControlEvent e) {
        System.out.println("register_request");

        String _id = signupPage.getSignupId();
        String _pw = signupPage.getSignupPw();
        String _pwConf = signupPage.getSignupPwConf();

        // 이거 왜 작동안함?
        if ( _id.toString().equals(null) || _id.toString().equals("") ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("회원가입 오류");
            alert.setContentText("아이디를 입력해주세요.");
            alert.show();
            return;
        }

        if (Queries.instance.checkID(_id)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("회원가입 오류");
            alert.setContentText("중복되는 id가 있습니다.");
            alert.show();
            return;
        }

        if (false) {
            // TODO: 0611 비밀번호 조건코드 추가
        }

        if ( !(_pw.toString() != _pwConf.toString()) ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("회원가입 오류");
            alert.setContentText("비밀번호가 서로 맞지 않습니다. 다시 입력해주세요.");
            alert.show();
            return;
        }


        boolean queryResult = Queries.instance.requestSignup(_id, _pw);

        if (!queryResult) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("회원가입 오류");
            alert.setContentText("알 수 없는 오류.");
            alert.show();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("회원가입 완료");
            alert.setContentText("회원가입이 완료되었습니다! 로그인을 진행해주세요.");
            alert.show();
        }

        // TODO: 되었다 가정하고 창 지우기
        titleTab.getChildren().remove(loginPage);
        titleTab.getChildren().remove(signupPage);
        titleTab.getChildren().remove(userPane);

        addUserPane();

        controller.menuTab.onclick_all_festivals();

        if (userPane == null) {
            return;
        }

        // TODO: 13131
        userPane.getElement(2).setOnAction(_e -> logAction(new ControlEvent(_e, EventCode.TITLE_LOGACTION)));
    }





}
