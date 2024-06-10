package app.fx.TEST.CustomClass;

import javafx.fxml.FXML;

public class Sample_CustomPaneController {

    @FXML
    private Sample_CustomPane customPane;

    @FXML
    public void initialize() {
        if (customPane != null) {
            System.out.println("CustomPane is successfully injected.");
            // 추가 초기화 코드 작성 가능
        } else {
            System.out.println("CustomPane injection failed.");
        }
    }
}
