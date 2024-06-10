package app.fx.Transport;

import app.fx.Data.USERS;
import app.fx._env;
import app.fx.elements.LoginPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController_V1 implements Initializable {
    @FXML private AnchorPane ROOT;

    private LoginPage loginPage;

    //database
    private static final String url = _env.getEnv("DATABASE_URL");
    private static final String id = _env.getEnv("DATABASE_ID");
    private static final String pw =_env.getEnv("DATABASE_PW");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginPage = new LoginPage();
        loginPage.setId("LoginPage");
        loginPage.loginButton.setOnAction(this::login);
        loginPage.registerButton.setOnAction(this::register);
        ROOT.getChildren().add(loginPage); // 생성자로 생성된 페이지 할당

    }

    private void login(ActionEvent event) {
        String userId = loginPage.getUsername();
        String password = loginPage.getPassword();
        if (validateLogin(userId, password)) {
            // 화면전환
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setContentText("로그인 되었습니다");
                alert.show();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/Merge/merge_travel_items.fxml"));
//                Pane nextScene = loader.load();
                Scene scene = new Scene(loader.load(), 1600, 900);

                String user_type = System.getenv("user_type");
                System.out.println(user_type);
                Stage stage = (Stage) ROOT.getScene().getWindow();
                stage.setScene(scene);
                System.out.println("로그인이 되었습니다.");
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
//        System.out.println("id: " + loginPage.getUsername());
//        System.out.println("pw: " + loginPage.getPassword());
    }

    private boolean validateLogin(String userId, String userpw) {
        boolean isValid = false;
        try (Connection conn = DriverManager.getConnection(url, id, pw)) {
            String query = "SELECT * FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ? AND USER_TYPE = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userpw);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }

    private void register(ActionEvent event) {
        System.out.println("회원가입을 진행합니다.");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/Transport/signUp.fxml"));
            Scene scene = new Scene(loader.load(), 600, 400);
            // Assuming you are getting the Stage from the RootPane
            Stage stage = (Stage) LoginPage.registerButton.getScene().getWindow();
            stage.setScene(scene);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
