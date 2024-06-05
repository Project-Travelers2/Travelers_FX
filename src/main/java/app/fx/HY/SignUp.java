package app.fx.HY;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader signUp = new FXMLLoader(getClass().getResource("/app/fx/HY/signUp.fxml"));
        Scene scene = new Scene(signUp.load(), 1600, 900);

        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }
}
