package app.fx.HA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NewItemMain_HA extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NewItem.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("New Item");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
