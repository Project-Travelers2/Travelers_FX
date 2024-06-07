package app.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import oracle.jdbc.OracleConnectionWrapper;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Login_Controller implements Initializable {
    @FXML
    private Pane RootPane;
    @FXML private TextField field_id;
    @FXML private PasswordField field_pw;
    @FXML private Button login_btn;
    @FXML private Button register_btn;
    @FXML private Button exit_btn;

    //database
    private static final String url = _env.getEnv("DATABASE_URL");
    private static final String id = _env.getEnv("DATABASE_ID");
    private static final String pw =_env.getEnv("DATABASE_PW");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("로그인 컨트롤러 실행");
        login_btn.setOnAction(event -> login());
        register_btn.setOnAction(event -> register());
        exit_btn.setOnAction(event -> exit());

    }

    /**
     *
     */
    @FXML
    private void login() {
        String userId = field_id.getText();
        String password = field_pw.getText();

        if (validateLogin(userId, password)) {
            // 화면전환
            try {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setContentText("로그인 되었습니다");
                alert.show();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/HY/merge_travel_items_HY.fxml"));
//                Pane nextScene = loader.load();
                Scene scene = new Scene(loader.load(), 1600, 900);

                // Assuming you are getting the Stage from the RootPane
                Stage stage = (Stage) RootPane.getScene().getWindow();
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
            System.out.println("로그인 실패");
        }
    }

    private boolean validateLogin(String userId, String userpw) {
        boolean isValid = false;

        try (Connection conn = DriverManager.getConnection(url, id, pw)) {
            String query = "SELECT * FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ?";
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

    /**
     * 로그인 창을 나갑니다.
     */
    @FXML
    private void exit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/HY/merge_travel_items_HY.fxml"));
//                Pane nextScene = loader.load();
            Scene scene = new Scene(loader.load(), 1600, 900);

            // Assuming you are getting the Stage from the RootPane
            Stage stage = (Stage) RootPane.getScene().getWindow();
            stage.setScene(scene);
            System.out.println("홈 화면");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void register()  {
        System.out.println("회원가입 버튼 클릭");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/HY/signUp.fxml"));
//                Pane nextScene = loader.load();
            Scene scene = new Scene(loader.load(), 1600, 900);

            // Assuming you are getting the Stage from the RootPane
            Stage stage = (Stage) RootPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
