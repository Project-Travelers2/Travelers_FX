package app.fx.elements;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

public class LoginPage extends Pane {

    public TextField username;
    public PasswordField password;
    public Button loginButton;
    public Button registerButton;

    public LoginPage() {
        this.setPrefSize(600.0, 400.0);
        this.setId("RootPane");

        Label tLbl = new Label("Login Page");
        tLbl.setPrefSize(360, 70);
        tLbl.setLayoutX(120.0);
        tLbl.setLayoutY(80.0);
        tLbl.setAlignment(Pos.CENTER);

        Label txtID = new Label("ID");
        txtID.setPrefSize(100, 20);
        txtID.setLayoutX(90.0);
        txtID.setLayoutY(150.0);
        txtID.setAlignment(Pos.CENTER_RIGHT);


        this.username = new TextField();
        this.username.setLayoutX(200.0);
        this.username.setLayoutY(150.0);
        this.username.setPrefSize(200.0, 22.0);
        this.username.setId("field_id");

        Label txtPW = new Label("Password");
        txtPW.setPrefSize(100, 20);
        txtPW.setLayoutX(90.0);
        txtPW.setLayoutY(180.0);
        txtPW.setAlignment(Pos.CENTER_RIGHT);

        this.password = new PasswordField();
        this.password.setLayoutX(200.0);
        this.password.setLayoutY(180.0);
        this.password.setPrefSize(200.0, 22.0);
        this.password.setId("field_password");


        this.loginButton = new Button("로그인");
        this.loginButton.setLayoutX(250.0);
        this.loginButton.setLayoutY(250.0);
        this.loginButton.setMnemonicParsing(false);
        this.loginButton.setPrefSize(100.0, 30.0);
        this.loginButton.setId("login_btn");

        this.registerButton = new Button("회원가입");
        this.registerButton.setLayoutX(250.0);
        this.registerButton.setLayoutY(300.0);
        this.registerButton.setMnemonicParsing(false);
        this.registerButton.setPrefSize(100.0, 30.0);
        this.registerButton.setId("register_btn");

        this.getChildren().addAll(tLbl, txtID, username, txtPW, password, loginButton, registerButton);
        System.out.println("Login Page Initialized");
    }

    public String getUsername() {
        return this.username.getText();
    }

    public String getPassword() {
        return this.password.getText();
    }

}
