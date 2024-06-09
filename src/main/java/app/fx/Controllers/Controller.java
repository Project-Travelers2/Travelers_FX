package app.fx.Controllers;

import app.fx.elements.Festival_item;

public abstract class Controller {

    /**
     * 축제 아이템 선택시 이벤트 실행
     * @param item 축제 아이템
     */
    public abstract void onclick_festival_item(Festival_item item);
}
