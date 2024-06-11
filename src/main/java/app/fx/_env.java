package app.fx;

import app.fx.Data.AIRPORT_INFORMATION;
import app.fx.Data.FESTIVALS;
import app.fx.Data.USERS;
import app.fx.HA.Queries;
import app.fx.elements.Festival_item;
import io.github.cdimascio.dotenv.Dotenv;

import java.time.LocalDate;
import java.util.List;

public class _env {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }

    public static List<FESTIVALS> festival_informations;
    public static List<USERS> users;
    public static USERS selected_user;

    private static Festival_item selected_festival;
    private static String arrivalCountryCode;

    public static AIRPORT_INFORMATION departure_information;
    public static AIRPORT_INFORMATION arrival_information;

    public static LocalDate departure_date;
    public static LocalDate arrival_date;

    /**
     * 아이템 페이지 속성 초기화
     */
    public static void ResetItemProperties()
    {
        festival_informations = null;
    }

    public static void main(String[] args) {
        System.out.println("DATABASE_URL: " + getEnv("DATABASE_URL"));
        System.out.println("DATABASE_ID : " + getEnv("DATABASE_ID"));
        System.out.println("DATABASE_PW : " + getEnv("DATABASE_PW"));
        System.out.println("API_KEY : " + getEnv("API_KEY"));
    }

    public static Festival_item getSelected_festival() {
        return selected_festival;
    }

    public static void setSelected_festival(Festival_item selected_festival) {
        _env.selected_festival = selected_festival;
        arrivalCountryCode = Queries.instance.get_country_code(selected_festival.getFest_info().local_id);
        System.out.println(arrivalCountryCode);
    }

    public static String getArrivalCountryCode() {
        return arrivalCountryCode;
    }
}
