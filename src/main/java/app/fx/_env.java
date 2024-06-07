package app.fx;

import app.fx.Data.AIRPORT_INFORMATION;
import app.fx.Data.FESTIVALS;
import app.fx.Data.USERS;
import app.fx.elements.Festival_item;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.Scene;

import java.time.LocalDate;
import java.util.List;

public class _env {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }

//    public static Scene scene;

    public static int pageNumber = 1;
    public static List<FESTIVALS> festival_informations;
    public static List<USERS> users;
    public static USERS selected_user;

    public static Festival_item selected_festival;

    public static AIRPORT_INFORMATION departure_information;
    public static AIRPORT_INFORMATION arrival_information;

    public static LocalDate departure_date;
    public static LocalDate arrival_date;

    /**
     * 아이템 페이지 속성 초기화
     */
    public static void ResetItemProperties()
    {
        pageNumber = 1;
        festival_informations = null;
    }

    public static void pageUp() throws Exception{
        if (festival_informations == null || festival_informations.size() == 0) {
            throw new Exception("festival_informations is null or Empty");
        }

        int upNum = pageNumber + 1;
        if (upNum > festival_informations.size() / 6) {
            throw new IndexOutOfBoundsException("festival_informations size overflow");
        }

        pageNumber = upNum;
    }

    public static void pageDown() throws Exception{
        if (festival_informations == null || festival_informations.size() == 0) {
            throw new Exception("festival_informations is null or Empty");
        }

        int downNum = pageNumber - 1;
        if (downNum == 0) {
            throw new IndexOutOfBoundsException("festival_informations index underflow");
        }

        pageNumber = downNum;
    }

    public static void main(String[] args) {
        System.out.println("DATABASE_URL: " + getEnv("DATABASE_URL"));
        System.out.println("DATABASE_ID : " + getEnv("DATABASE_ID"));
        System.out.println("DATABASE_PW : " + getEnv("DATABASE_PW"));
        System.out.println("API_KEY : " + getEnv("API_KEY"));
    }
}
