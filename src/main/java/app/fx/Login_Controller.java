package app.fx;

import app.fx.Data.USERS;
import app.fx.HY.TestMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login_Controller {

    @FXML
    private Pane RootPane;
    @FXML private TextField field_id;
    @FXML private TextField field_pw;
    @FXML private Button login_btn;
    @FXML private Button register_btn;
    private TestMember tm;
    public static USERS db ;



    /**
     *
     * @param event
     */
    @FXML
    private void login(ActionEvent event) {
        String f_id = field_id.getText();
        String f_pw = field_pw.getText();

        if (field_id.getText().equals("field_id") && field_pw.equals("field_pw")) {
            try {
                System.out.println("Login Success");
                Stage main_stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("merge_travel_items_HY.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login Failed");
        }
//        db = new USERS();
//        tm = new TestMember();
    }



    /**
     * 로그인 창을 나갑니다.
     * @param event 나가기 버튼 클릭
     */
    @FXML
    private void exit(ActionEvent event) {
        System.out.println("exit click");
        _env.ResetItemProperties();
    }

    @FXML
    private void register(ActionEvent event) {
        System.out.println("register click");
        if (event.getSource() == register_btn) {

        }
    }
}
