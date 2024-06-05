package app.fx.elements;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SignUpPage extends Pane {

    public Button exitBtn;
    public TextField username;
    public PasswordField password;
    public PasswordField confirmPassword;
    public Button registerButton;

    public SignUpPage() {
        this.setId("ROOT");
        this.setPrefSize(600.0, 400.0);

        // Button
        exitBtn = new Button("");
        exitBtn.setPrefSize(600.0, 400.0);

        // Label
        Label title = new Label("Sign Up");
        title.setAlignment(Pos.CENTER);
        title.setLayoutX(122.0);
        title.setLayoutY(20.0);
        title.setPrefSize(353, 64);


        HBox box1 = new HBox();
        box1.setAlignment(Pos.CENTER);
        box1.setLayoutX(190.0);
        box1.setLayoutY(90.0);
        box1.setPrefSize(227.0, 22.0);

        // ID
        Label txtID = new Label("ID");
        txtID.setPrefSize(100.0, 22.0);
        username = new TextField("USER_NAME");
        username.setPrefSize(227.0, 22.0);
        box1.getChildren().addAll(txtID, username);

        HBox box2 = new HBox();
        box2.setLayoutX(190.0);
        box2.setLayoutY(140.0);
        box2.setPrefSize(227.0, 22.0);
        
        // PW
        Label txtPW = new Label("비밀번호");
        txtPW.setPrefSize(100.0, 22.0);
        password = new PasswordField();
        password.setPrefSize(227.0, 22.0);
        box2.getChildren().addAll(txtPW, password);


        HBox box3 = new HBox();
        box3.setLayoutX(190.0);
        box3.setLayoutY(190.0);
        box3.setPrefSize(227.0, 22.0);

        // PW_confirm
        Label txtConfirm = new Label("확인");
        txtConfirm.setPrefSize(100.0, 22.0);
        confirmPassword = new PasswordField();
        confirmPassword.setPrefSize(227.0, 22.0);
        box3.getChildren().addAll(txtConfirm, confirmPassword);


        // Button
        registerButton = new Button("가입하기");
        registerButton.setPrefSize(172.0, 58.0);
        registerButton.setMnemonicParsing(false);
        registerButton.setLayoutX(217.0);
        registerButton.setLayoutY(283.0);
        registerButton.setId("register_btn");


        this.getChildren().addAll(exitBtn, title, box1, box2, box3, registerButton);
    }
}
