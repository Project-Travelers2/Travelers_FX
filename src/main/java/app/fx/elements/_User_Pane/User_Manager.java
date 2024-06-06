package app.fx.elements._User_Pane;

import app.fx.Data.USERS;
import app.fx.elements.User_Pane;
import javafx.scene.control.Button;

public class User_Manager extends User_Pane {

    // 현재 사용자의 정보를 할당한다. (여긴 없지만.)
    public User_Manager(USERS currUser) {
        super();
        this.selectedUser = currUser;

        // Button 생성 및 추가
        Button langBtn = new Button("언어");
        langBtn = new Button("최종 관리자");
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
        loginBtn.setId("loginBtn");
        loginBtn.setPrefSize(100.0, 30.0);
        this.add(loginBtn, 2, 0);
        grids.add(loginBtn);
    }

    @Override
    public Button getElement(int index) throws IndexOutOfBoundsException{
        if (index < 0 || index >= grids.size()) {
            throw new IndexOutOfBoundsException();
        }
        return grids.get(index);
    }
}
