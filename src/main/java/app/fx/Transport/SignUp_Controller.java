package app.fx.Transport;

import app.fx.elements.SignUpPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp_Controller implements Initializable {

    @FXML
    private AnchorPane ROOT;

    private SignUpPage signUpPage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signUpPage = new SignUpPage();
        signUpPage.setId("SignUpPage");
        signUpPage.exitBtn.setOnAction(this::windowExit);
        signUpPage.registerButton.setOnAction(this::register);

        ROOT.getChildren().add(signUpPage);
    }

    private void register(ActionEvent event) {
        System.out.println("register button clicked");
    }

    private void windowExit(ActionEvent event) {
        System.out.println("exit button clicked");
    }
}
