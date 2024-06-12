package app.fx.HA;

import app.fx._env;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class Reservation {
    public void doReservation() {
        // TODO: 0611 URL 작성, 데이터베이스에 데이터 저장
        // 조건을 만족했다면, URL을 생성하고, 데이터를 데이터베이스에 저장하는 코드를 작성합니다.

        // 필요 데이터
        int user_id = _env.selected_user.user_id; // 사용자 id (_env.selected_user.user_name(하려고 했는데 걍 user_id로))
        String schedule_name = "schedule_name";
        LocalDate departure_date = _env.departure_date;   // 출발일
        LocalDate arrival_date = _env.arrival_date;    // 도착일
        String departure_airport_id = _env.departure_information.airport_id;   // 출발 공항
        String arrival_airport_id = _env.arrival_information.airport_id;      // 도착 공항
        String festival_id = _env.getSelected_festival().getFest_info().festival_id;         // 축제 id

        String departure_airport_iata = _env.departure_information.iata_code;
        String arrival_airport_iata = _env.arrival_information.iata_code;

        boolean isFinished = Queries.instance.addSchedule(user_id, schedule_name, departure_date, arrival_date, departure_airport_id, arrival_airport_id, festival_id);

        // 현재까지 선택한 정보들 출력하기
        System.out.println("=============================");
        System.out.println("현재 선택한 정보들");
        System.out.println("유저 id : " + user_id);
//        System.out.println("스케줄명: " + schedule_name);
        System.out.println("출발일 : " + _env.departure_date);
        System.out.println("도착일 : " + _env.arrival_date);
        System.out.println("출발 공항 : " + _env.departure_information);
        System.out.println("도착 공항 : " + _env.arrival_information);
        System.out.println("축제 id : " + festival_id);
        System.out.println("=============================");

        if (isFinished) {
            try {
//                URI uri = new URI("https://www.skyscanner.co.kr/transport/flights/sela/nrt/240613/240719/?adultsv2=1&cabinclass=economy&childrenv2=&ref=home&rtn=1&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false");
                // #출발공항 : departure_airport_iata
                // #도착공항 : arrival_airport_iata
                // #출발일 : _env.departure_date
                // #복귀일 : _env.arrival_date
                // #성인수 없으면 아무것도 적지않음 : 1명 기본
                // #아이수 없으면 아무것도 적지않음 : 0
                // TODO: 0612 공항코드 필요 (airport 연동해야함)

                String adultCount = "1";
                String childCount = "";
                String uriPath = "https://www.skyscanner.co.kr/transport/flights/" +
                                departure_airport_iata + "/" +
                                arrival_airport_iata + "/" +
                                _env.departure_date + "/" +
                                _env.arrival_date + "/" +
                                "?adultsv2=" + adultCount +
                                "&cabinclass=economy" +
                                "&childrenv2=" + childCount +
                                "&ref=home&rtn=1&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false";

                System.out.println(uriPath);
                URI uri = new URI(uriPath);
//                URI uri = new URI("https://www.skyscanner.co.kr/transport/flights/[#출발공항]/[#도착공항]/[#출발일]/[#복귀일]/?adultsv2=[#성인수]&cabinclass=economy&childrenv2=[#아이수]&ref=home&rtn=1&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false");

                // Desktop 객체를 사용하여 브라우저 열기
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    if (desktop.isSupported(Desktop.Action.BROWSE)) {
                        desktop.browse(uri);
                    } else {
                        System.out.println("Desktop browsing is not supported on this system.");
                    }
                } else {
                    System.out.println("Desktop is not supported on this system.");
                }

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("실패");
        }

        // DB 이력넣기

        // URL 조합
    }
}
