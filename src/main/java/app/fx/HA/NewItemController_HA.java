package app.fx.HA;

import app.fx.Data.USERS;
import app.fx._env;
import app.fx.elements.User_Pane;
import app.fx.elements._User_Pane.User_Customer;
import app.fx.elements._User_Pane.User_LoginRequired;
import app.fx.elements._User_Pane.User_Manager;
import app.fx.elements._User_Pane.User_Staff;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class NewItemController_HA implements Initializable {

    @FXML
    AnchorPane ROOT;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        USERS userTest = new USERS();
        userTest.user_type = "3";
        _env.selected_user = userTest;

        // 유저 레벨 0 (로그인 안됨)
        if (_env.selected_user == null || _env.selected_user.user_type == "0") {
            // 기본 뷰 할당
            User_Pane pane = new User_LoginRequired(_env.selected_user);
            ROOT.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "1") {
            User_Pane pane = new User_Customer(_env.selected_user);
            ROOT.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "2") {
            User_Pane pane = new User_Staff(_env.selected_user);
            ROOT.getChildren().add(pane);
        } else if (_env.selected_user.user_type == "3") {
            User_Pane pane = new User_Manager(_env.selected_user);
            ROOT.getChildren().add(pane);
        }
    }


}
