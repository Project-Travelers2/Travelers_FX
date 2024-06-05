package app.fx.Transport;

import app.fx.elements.LoginPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController_V1 implements Initializable {
    @FXML private AnchorPane ROOT;

    private LoginPage loginPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginPage = new LoginPage();
        loginPage.setId("LoginPage");
        loginPage.loginButton.setOnAction(this::login);
        loginPage.registerButton.setOnAction(this::register);
        ROOT.getChildren().add(loginPage); // 생성자로 생성된 페이지 할당

    }

    private void login(ActionEvent event) {
        System.out.println("id: " + loginPage.getUsername());
        System.out.println("pw: " + loginPage.getPassword());
    }

    private void register(ActionEvent event) {
        System.out.println("회원가입을 진행합니다.");
    }
}
