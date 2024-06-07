package app.fx.elements;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import org.w3c.dom.Text;

public class LoginPage extends Pane {

    public TextField username;
    public PasswordField password;
    public Button loginButton;
    public static Button registerButton;

    public LoginPage() {
        this.setPrefSize(600.0, 400.0);
        this.setId("RootPane");

        Label tLbl = new Label("FESTI + VEL");
        tLbl.setPrefSize(360, 70);
        tLbl.setLayoutX(120.0);
        tLbl.setLayoutY(80.0);
        tLbl.setAlignment(Pos.CENTER);
        tLbl.setFont(Font.font("배달의민족 도현"));
        tLbl.setTextFill(Paint.valueOf("#89C3FBFF"));


        Label txtID = new Label("ID");
        txtID.setPrefSize(100, 20);
        txtID.setLayoutX(90.0);
        txtID.setLayoutY(150.0);
        txtID.setAlignment(Pos.CENTER_RIGHT);
        txtID.setFont(Font.font("배달의민족 도현"));



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
        txtPW.setFont(Font.font("배달의민족 도현"));

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
        loginButton.setFont(Font.font("배달의민족 도현"));
        loginButton.setStyle("-fx-background-color: #89c3fb");
        loginButton.setTextFill(Paint.valueOf("white"));

        this.registerButton = new Button("회원가입");
        this.registerButton.setLayoutX(250.0);
        this.registerButton.setLayoutY(290.0);
        this.registerButton.setMnemonicParsing(false);
        this.registerButton.setPrefSize(100.0, 30.0);
        this.registerButton.setId("register_btn");
        registerButton.setFont(Font.font("배달의민족 도현"));
        registerButton.setStyle("-fx-background-color: #89c3fb");
        registerButton.setTextFill(Paint.valueOf("white"));

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
