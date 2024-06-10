package app.fx.Control;

import app.fx.Data.EventCode;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

/**
 * 커스텀 이벤트 코드
 */
public class ControlEvent extends ActionEvent {
    private EventCode eventCode;

    public ControlEvent(EventCode eventCode) {
        this.eventCode = eventCode;
    }

    public EventCode getEventCode() {
        return eventCode;
    }
}
