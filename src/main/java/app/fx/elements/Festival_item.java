package app.fx.elements;

import app.fx.Data.FESTIVAL_INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class Festival_item extends Button {

    private FESTIVAL_INFORMATION fest_info;

    public Festival_item() {
        this.setPrefSize(515, 280);
        this.setFont(new Font("Arial", 24));
        this.setId("FESTIVAL_ITEM");
    }

    public Festival_item(FESTIVAL_INFORMATION fest_info) {
        this();
        this.fest_info = fest_info;
        this.setText(fest_info.festival_name);
    }


}
