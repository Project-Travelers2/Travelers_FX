package app.fx.TEST;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Sample_ImageViewZoom extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/fx/HA/Sample_ImageViewZoom.fxml"));
        Scene scene = new Scene(loader.load(), 1600, 900);
        Sample_ImageViewZoomController controller = loader.getController();
        primaryStage.setTitle("Zoom image");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Load an image
        Image image = new Image(getClass().getResource("/app/fx/HA/1101_부산국제영화제.jpg").toExternalForm());

        // Create an ImageView
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(280);
        imageView.setFitWidth(514);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        // Add click event to ImageView
        controller.ROOT.getChildren().add(imageView);
        imageView.setOnMouseClicked(event -> showZoomedImage(image));
    }

    private void showZoomedImage(Image image) {
        // Create a new stage for the zoomed image
        Stage zoomStage = new Stage();
        zoomStage.initModality(Modality.APPLICATION_MODAL);

        // Create an ImageView for the zoomed image
        ImageView zoomedImageView = new ImageView(image);
        zoomedImageView.setFitHeight(560);
        zoomedImageView.setFitWidth(1028);
        zoomedImageView.setPreserveRatio(true);

        // Create a close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> zoomStage.close());

        // Set up the layout and scene for the zoomed image
        VBox vbox = new VBox(zoomedImageView, closeButton);
        Scene zoomScene = new Scene(vbox);

        // Set up the zoom stage
        zoomStage.setScene(zoomScene);
        zoomStage.setTitle("Zoomed Image");
        zoomStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
