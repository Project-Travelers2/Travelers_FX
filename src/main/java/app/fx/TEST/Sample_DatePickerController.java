package app.fx.TEST;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;

import java.time.LocalDate;

public class Sample_DatePickerController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button dateButton;

    @FXML
    private void handleDateButtonAction(ActionEvent event) {
        // DatePicker에서 선택한 날짜를 가져옴
        LocalDate selectedDate = datePicker.getValue();

        if (selectedDate != null) {
            // 선택한 날짜를 출력
            System.out.println("Selected date: " + selectedDate.toString());
        } else {
            System.out.println("No date selected");
        }
    }
}
