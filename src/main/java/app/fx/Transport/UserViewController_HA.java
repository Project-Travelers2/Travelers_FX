package app.fx.Transport;

import app.fx.Data.USERS;
import app.fx._env;
import app.fx.elements.User_Pane;
import app.fx.elements._User_Pane.User_Customer;
import app.fx.elements._User_Pane.User_LoginRequired;
import app.fx.elements._User_Pane.User_Manager;
import app.fx.elements._User_Pane.User_Staff;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class UserViewController_HA implements Initializable {

    @FXML
    AnchorPane ROOT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        USERS userTest = new USERS();
        userTest.user_type = "3";
        _env.selected_user = userTest;

        User_Pane pane = null;
        // 유저 레벨 0 (로그인 안됨)
        if (_env.selected_user == null || _env.selected_user.user_type == "0") {
            // 기본 뷰 할당
            pane = new User_LoginRequired(_env.selected_user);
            ROOT.getChildren().add(pane);

        } else if (_env.selected_user.user_type == "1") {
            pane = new User_Customer(_env.selected_user);
            ROOT.getChildren().add(pane);

        } else if (_env.selected_user.user_type == "2") {
            pane = new User_Staff(_env.selected_user);
            ROOT.getChildren().add(pane);

        } else if (_env.selected_user.user_type == "3") {
            pane = new User_Manager(_env.selected_user);
            ROOT.getChildren().add(pane);

        }

        if (pane == null) {
            return;
        }

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

    private void login_required(ActionEvent event) {
        System.out.println("0: 로그인 요청");

        // 로그인이 완료되었다면
        // _env.selectedUser에 받아온 유저 정보를 할당
        // 이 정보는 user_type이 1,2,3중에 하나임
    }

    private void logout_customer(ActionEvent event) {
        System.out.println("1: 사용자 로그아웃");

        // user_type이 1
    }

    private void logout_staff(ActionEvent event) {
        System.out.println("2: 직원 로그아웃");

        // user_type이 2
    }

    private void logout_manager(ActionEvent event) {
        System.out.println("3: 관리자 로그아웃");

        // user_type이 3
    }
}
