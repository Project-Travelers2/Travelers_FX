package app.fx.HA;

import app.fx._env;

public class Reservation {
    public void doReservation() {
        // TODO: 0611 URL 작성, 데이터베이스에 데이터 저장
        // 조건을 만족했다면, URL을 생성하고, 데이터를 데이터베이스에 저장하는 코드를 작성합니다.

        // 현재까지 선택한 정보들 출력하기
        System.out.println("=============================");
        System.out.println("현재 선택한 정보들");
        System.out.println("여행 정보 : " + _env.selected_festival);
        System.out.println("출발 공항 : " + _env.departure_information);
        System.out.println("도착 공항 : " + _env.arrival_information);
        System.out.println("출발일 : " + _env.departure_date);
        System.out.println("도착일 : " + _env.arrival_date);
        System.out.println("=============================");

        // DB 이력넣기

        // URL 조합
    }
}
