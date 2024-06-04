package app.fx.HY;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class Main_HY extends Application {
    @Override
    public void start(Stage stage) throws IOException{
        /*폰트 */

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/app/fx/HY/merge_travel_items_HY.fxml"));
        Scene scene = new Scene(fxmlloader.load(), 1600, 900);



        // css 연결하기
//        scene.getStylesheets().add(getClass().getResource("festival.css").toString());

        scene.getStylesheets().add(getClass().getResource("festival.css").toString());

        stage.setTitle("Travel items111");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
