package app.fx.elements;

import app.fx.Controller_V2;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.util.Duration;

public class CustomPopup {
    private Controller_V2 controller;

    public CustomPopup(Controller_V2 controller) {
        this.controller = controller;
    }

    public void festivalNotSelected() {
        Popup popup = new Popup();
        StackPane popupContent = new StackPane();
        popupContent.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-padding: 32px;");
        Label label = new Label("축제가 선택되지 않았습니다. 축제를 선택해주세요");
        label.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        popupContent.getChildren().add(label);
        popup.getContent().add(popupContent);

        popup.show(controller.currentStage);

        // 팝업 애니메이션 설정
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(popup.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(2), new KeyValue(popup.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(4), new KeyValue(popup.opacityProperty(), 0.0))
        );

        // 애니메이션 끝난 후 팝업 닫기
        timeline.setOnFinished(event -> popup.hide());

        // 애니메이션 시작
        timeline.play();
    }
}
