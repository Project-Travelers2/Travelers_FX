package app.fx.Control;

import app.fx.Data.EventCode;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

/**
 * 커스텀 이벤트 코드
 */
public class ControlEvent extends ActionEvent {
    public Event event;
//    public ActionEvent event;
//    public MouseEvent mouseEvent;
    private EventCode eventCode;

    public ControlEvent(Event event, EventCode eventCode) {
        this.event = event;
        this.eventCode = eventCode;
    }

    public EventCode getEventCode() {
        return eventCode;
    }
}
