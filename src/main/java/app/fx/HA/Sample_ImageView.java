package app.fx.HA;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Sample_ImageView extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 이미지 파일의 경로
        // 절대경로
//        String imagePath = "file:C:\\Projects\\Project1_Travelers\\Travelers_FX\\src\\main\\resources\\app\\fx\\HA/1101_부산국제영화제.jpg"; // 로컬 파일 경로
        String imagePath = "file:/src/main/app/fx/HA/1101_부산국제영화제.jpg";
        // String imagePath = "http://example.com/image.jpg"; // 웹 URL 경로

        // Image 객체 생성
        Image image = new Image(imagePath);

        // ImageView 객체 생성 및 이미지 설정
        ImageView imageView = new ImageView(image);

        // ImageView의 크기 조정 (선택 사항)
        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true); // 원본 비율 유지

        // 레이아웃 설정
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        // 씬 설정
        Scene scene = new Scene(root, 600, 400);

        // 스테이지 설정
        primaryStage.setTitle("ImageView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
