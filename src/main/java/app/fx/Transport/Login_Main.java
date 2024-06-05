package app.fx.Transport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login_Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/fx/Transport/login_test.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginController_V1 controller = fxmlLoader.getController();

        //css
       // scene.getStylesheets().add(getClass().getResource("login.css").toString());

        stage.setTitle("login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
