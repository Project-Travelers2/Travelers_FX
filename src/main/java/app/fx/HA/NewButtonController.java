package app.fx.HA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NewButtonController {

    @FXML
    private Button newButton;

    @FXML
    private void handleButtonClick(ActionEvent event) {
        System.out.println("New button clicked");
    }

    @FXML
    public Button getNewButton() {
        return newButton;
    }
}
