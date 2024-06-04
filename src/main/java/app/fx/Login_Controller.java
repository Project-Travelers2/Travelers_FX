package app.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Login_Controller {

    @FXML
    private Pane RootPane;
    @FXML private TextField field_id;
    @FXML private TextField field_pw;
    @FXML private Button login_btn;
    @FXML private Button register_btn;

    /**
     *
     * @param event
     */
    @FXML
    private void login(ActionEvent event) {

    }

    /**
     * 로그인 창을 나갑니다.
     * @param event 나가기 버튼 클릭
     */
    @FXML
    private void exit(ActionEvent event) {

    }

    @FXML
    private void register(ActionEvent event) {

    }
}
