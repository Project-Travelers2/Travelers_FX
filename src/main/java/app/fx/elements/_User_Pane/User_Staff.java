package app.fx.elements._User_Pane;

import app.fx.Data.USERS;
import app.fx.elements.User_Pane;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class User_Staff extends User_Pane {

    // 현재 사용자의 정보를 할당한다. (여긴 없지만.)
    public User_Staff(USERS currUser) {
        super();
        this.selectedUser = currUser;

        // Button 생성 및 추가
        Button langBtn = new Button("언어");
        langBtn = new Button("고객사 직원");
        langBtn.setId("langBtn");
        langBtn.setPrefSize(100.0, 30.0);
        this.add(langBtn, 0, 0);
        langBtn.setTextFill(Paint.valueOf("white"));
        langBtn.setFont(Font.font("배달의민족 도현",15));
        langBtn.setStyle("-fx-background-color: #8ac5fd");
        grids.add(langBtn);

        Button favBtn = new Button("즐겨찾기");
        favBtn.setId("favBtn");
        favBtn.setPrefSize(100.0, 30.0);
        this.add(favBtn, 1, 0);
        favBtn.setTextFill(Paint.valueOf("white"));
        favBtn.setFont(Font.font("배달의민족 도현",15));
        favBtn.setStyle("-fx-background-color: #8ac5fd");
        grids.add(favBtn);

        Button loginBtn = new Button("로그아웃");
        loginBtn.setId("logout_staff");
        loginBtn.setPrefSize(100.0, 30.0);
        this.add(loginBtn, 2, 0);
        loginBtn.setTextFill(Paint.valueOf("white"));
        loginBtn.setFont(Font.font("배달의민족 도현",15));
        loginBtn.setStyle("-fx-background-color: #8ac5fd");
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
