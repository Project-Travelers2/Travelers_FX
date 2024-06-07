package app.fx.elements._User_Pane;

import app.fx.Data.USERS;
import app.fx.Transport.UserViewController_HA;
import app.fx.elements.User_Pane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class User_Customer extends User_Pane {

    // 현재 사용자의 정보를 할당한다. (여긴 없지만.)
    public User_Customer(USERS currUser) {
        super();
        this.selectedUser = currUser;

        // Button 생성 및 추가
        Button langBtn = new Button("언어");
        langBtn = new Button("일반 사용자");
        langBtn.setId("langBtn");
        langBtn.setPrefSize(100.0, 30.0);
        this.add(langBtn, 0, 0);
        grids.add(langBtn);

        Button favBtn = new Button("즐겨찾기");
        favBtn.setId("favBtn");
        favBtn.setPrefSize(100.0, 30.0);
        this.add(favBtn, 1, 0);
        grids.add(favBtn);

        Button loginBtn = new Button("로그아웃");
        loginBtn.setId("logout_customer");
        loginBtn.setPrefSize(100.0, 30.0);
        this.add(loginBtn, 2, 0);
        grids.add(loginBtn);
//        loginBtn.setOnAction(event -> logAction(event));
    }

    @Override
    public Button getElement(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= grids.size()) {
            throw new IndexOutOfBoundsException();
        }
        return grids.get(index);
    }
}
