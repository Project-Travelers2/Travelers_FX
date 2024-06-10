package app.fx.TEST.CustomClass;

import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class Sample_CustomPane extends Pane {

    public Sample_CustomPane() {
        Label label = new Label("Custom Pane");
        this.getChildren().add(label);
    }

}
