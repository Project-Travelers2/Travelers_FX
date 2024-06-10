package app.fx.elements;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class SignupPage extends Pane {

    private final Pane signupPane;
    public Pane exitPane;
    private final TextField idField;
    private final PasswordField pwField;
    private final PasswordField pwConfField;
    public Button registerButton;

    public SignupPage() {
        // Initialize the root Pane
        signupPane = new Pane();
        signupPane.setPrefHeight(900.0);
        signupPane.setPrefWidth(1600.0);

        // Initialize and add the EXIT Pane
        exitPane = new Pane();
        exitPane.setPrefHeight(900.0);
        exitPane.setPrefWidth(1600.0);
        signupPane.getChildren().add(exitPane);

        // Initialize the inner Pane
        Pane innerPane = new Pane();
        innerPane.setLayoutX(500.0);
        innerPane.setLayoutY(250.0);
        innerPane.setPrefHeight(400.0);
        innerPane.setPrefWidth(600.0);

        // Initialize and add the Rectangle
        Rectangle backgroundRect = new Rectangle(600.0, 400.0);
        backgroundRect.setArcHeight(5.0);
        backgroundRect.setArcWidth(5.0);
        backgroundRect.setFill(Color.DODGERBLUE);
        backgroundRect.setStroke(Color.BLACK);
//        backgroundRect.setStrokeType(Rectangle.StrokeType.INSIDE);
        innerPane.getChildren().add(backgroundRect);

        // Initialize and add the Label
        Label titleLabel = new Label("회원가입");
        titleLabel.setAlignment(javafx.geometry.Pos.CENTER);
        titleLabel.setLayoutX(122.0);
        titleLabel.setLayoutY(20.0);
        titleLabel.setPrefHeight(64.0);
        titleLabel.setPrefWidth(353.0);
        titleLabel.setFont(new Font(41.0));
        innerPane.getChildren().add(titleLabel);

        // Initialize and add the ID TextField
        HBox idBox = new HBox();
        idBox.setLayoutX(190.0);
        idBox.setLayoutY(90.0);
        idBox.setPrefHeight(22.0);
        idBox.setPrefWidth(227.0);
        idField = new TextField("ID");
        idField.setPrefHeight(22.0);
        idField.setPrefWidth(227.0);
        idBox.getChildren().add(idField);
        innerPane.getChildren().add(idBox);

        // Initialize and add the PasswordField
        HBox pwBox = new HBox();
        pwBox.setLayoutX(190.0);
        pwBox.setLayoutY(140.0);
        pwBox.setPrefHeight(22.0);
        pwBox.setPrefWidth(227.0);
        pwField = new PasswordField();
        pwField.setPromptText("PASSWORD");
        pwField.setPrefHeight(22.0);
        pwField.setPrefWidth(227.0);
        pwBox.getChildren().add(pwField);
        innerPane.getChildren().add(pwBox);

        // Initialize and add the Password Confirmation Field
        HBox pwConfBox = new HBox();
        pwConfBox.setLayoutX(190.0);
        pwConfBox.setLayoutY(190.0);
        pwConfBox.setPrefHeight(22.0);
        pwConfBox.setPrefWidth(227.0);
        pwConfField = new PasswordField();
        pwConfField.setPromptText("PASSWORD");
        pwConfField.setPrefHeight(22.0);
        pwConfField.setPrefWidth(227.0);
        pwConfBox.getChildren().add(pwConfField);
        innerPane.getChildren().add(pwConfBox);

        // Initialize and add the Register Button
        registerButton = new Button("가입하기");
        registerButton.setLayoutX(217.0);
        registerButton.setLayoutY(283.0);
        registerButton.setPrefHeight(58.0);
        registerButton.setPrefWidth(172.0);
        innerPane.getChildren().add(registerButton);

        signupPane.getChildren().add(innerPane);
        this.getChildren().add(signupPane);
    }

    // Getters for accessing the elements if needed
    public Pane getSignupPane() {
        return signupPane;
    }

    public Pane getExitPane() {
        return exitPane;
    }

    public String getSignupId() { return idField.toString(); }

    public String getSignupPw() {
        return pwField.toString();
    }

    public String getSignupPwConf() {
        return pwConfField.toString();
    }

    public Button getRegisterButton() {
        return registerButton;
    }
}


//public class SignUpPage extends Pane {
//
//    public Button exitBtn;
//    public TextField username;
//    public PasswordField password;
//    public PasswordField confirmPassword;
//    public Button registerButton;
//
//    public SignUpPage() {
//        this.setId("ROOT");
//        this.setPrefSize(600.0, 400.0);
//
//        // Button
//        exitBtn = new Button("");
//        exitBtn.setPrefSize(600.0, 400.0);
//
//        // Label
//        Label title = new Label("Sign Up");
//        title.setAlignment(Pos.CENTER);
//        title.setLayoutX(122.0);
//        title.setLayoutY(20.0);
//        title.setPrefSize(353, 64);
//
//
//        HBox box1 = new HBox();
//        box1.setAlignment(Pos.CENTER);
//        box1.setLayoutX(190.0);
//        box1.setLayoutY(90.0);
//        box1.setPrefSize(227.0, 22.0);
//
//        // ID
//        Label txtID = new Label("ID");
//        txtID.setPrefSize(100.0, 22.0);
//        username = new TextField("USER_NAME");
//        username.setPrefSize(227.0, 22.0);
//        box1.getChildren().addAll(txtID, username);
//
//        HBox box2 = new HBox();
//        box2.setLayoutX(190.0);
//        box2.setLayoutY(140.0);
//        box2.setPrefSize(227.0, 22.0);
//
//        // PW
//        Label txtPW = new Label("비밀번호");
//        txtPW.setPrefSize(100.0, 22.0);
//        password = new PasswordField();
//        password.setPrefSize(227.0, 22.0);
//        box2.getChildren().addAll(txtPW, password);
//
//
//        HBox box3 = new HBox();
//        box3.setLayoutX(190.0);
//        box3.setLayoutY(190.0);
//        box3.setPrefSize(227.0, 22.0);
//
//        // PW_confirm
//        Label txtConfirm = new Label("확인");
//        txtConfirm.setPrefSize(100.0, 22.0);
//        confirmPassword = new PasswordField();
//        confirmPassword.setPrefSize(227.0, 22.0);
//        box3.getChildren().addAll(txtConfirm, confirmPassword);
//
//
//        // Button
//        registerButton = new Button("가입하기");
//        registerButton.setPrefSize(172.0, 58.0);
//        registerButton.setMnemonicParsing(false);
//        registerButton.setLayoutX(217.0);
//        registerButton.setLayoutY(283.0);
//        registerButton.setId("register_btn");
//
//
//        this.getChildren().addAll(exitBtn, title, box1, box2, box3, registerButton);
//    }
//}
