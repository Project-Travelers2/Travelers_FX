package app.fx.HA;

import app.fx._env;

import java.time.LocalDate;

public class Reservation {
    public void doReservation() {
        // TODO: 0611 URL 작성, 데이터베이스에 데이터 저장
        // 조건을 만족했다면, URL을 생성하고, 데이터를 데이터베이스에 저장하는 코드를 작성합니다.

        // 필요 데이터
        int schedule_id = 1; // 스케줄 id (이거 int로 바꾸고 싶다 ㄹㅇ)
        int user_id = _env.selected_user.user_id; // 사용자 id (_env.selected_user.user_name(하려고 했는데 걍 user_id로))
        String schedule_name = "schedule_name" + schedule_id;
        LocalDate departure_date = _env.departure_date;   // 출발일
        LocalDate arrival_date = _env.arrival_date;    // 도착일
        String departure_airport_id = _env.departure_information.airport_id;   // 출발 공항
        String arrival_airport_id = _env.arrival_information.airport_id;      // 도착 공항
        String festival_id = _env.getSelected_festival().getFest_info().festival_id;         // 축제 id

        boolean isFinished = Queries.instance.addSchedule(schedule_id, user_id, schedule_name, departure_date, arrival_date, departure_airport_id, arrival_airport_id, festival_id);

        // 현재까지 선택한 정보들 출력하기
        System.out.println("=============================");
        System.out.println("현재 선택한 정보들");
        System.out.println("여행 정보 : " + _env.getSelected_festival());
        System.out.println("출발 공항 : " + _env.departure_information);
        System.out.println("도착 공항 : " + _env.arrival_information);
        System.out.println("출발일 : " + _env.departure_date);
        System.out.println("도착일 : " + _env.arrival_date);
        System.out.println("=============================");

        if (isFinished) {
            System.out.println("완료");
            // TODO: 13133 0611 URL 코드 넣고 skyscanner로 리다이렉션

        } else {
            System.out.println("실패");
        }

        // DB 이력넣기

        // URL 조합
    }
}
