package app.fx.elements._User_Pane;

import app.fx.Data.USERS;
import app.fx._env;
import app.fx.elements.User_Pane;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

public class User_LoginRequired extends User_Pane {

    // 현재 사용자의 정보를 할당한다. (여긴 없지만.)
    public User_LoginRequired(USERS currUser) {
        super();
        this.selectedUser = currUser;

        // Button 생성 및 추가
        Button langBtn = new Button("언어");
        langBtn = new Button("언어");
        langBtn.setId("langBtn");
        langBtn.setPrefSize(100.0, 30.0);
        this.add(langBtn, 0, 0);
        grids.add(langBtn);
        langBtn.setTextFill(Paint.valueOf("white"));
        langBtn.setStyle("-fx-background-color: #8ac5fd");

        Button favBtn = new Button("즐겨찾기");
        favBtn.setId("favBtn");
        favBtn.setPrefSize(100.0, 30.0);
        this.add(favBtn, 1, 0);
        grids.add(favBtn);
        favBtn.setTextFill(Paint.valueOf("white"));
        favBtn.setStyle("-fx-background-color: #8ac5fd");

        Button loginBtn = new Button("로그인");
        loginBtn.setId("login_required");
        loginBtn.setPrefSize(100.0, 30.0);
        this.add(loginBtn, 2, 0);
        grids.add(loginBtn);
        loginBtn.setTextFill(Paint.valueOf("white"));
        loginBtn.setStyle("-fx-background-color: #8ac5fd");

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
