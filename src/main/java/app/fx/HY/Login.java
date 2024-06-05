package app.fx.HY;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/app/fx/HY/login.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1600, 900);

        // css 연결하기
//        scene.getStylesheets().add(getClass().getResource("festival.css").toString());

//        scene.getStylesheets().add(getClass().getResource("festival.css").toString());

        stage.setTitle("Travel Login");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) { launch();}
}