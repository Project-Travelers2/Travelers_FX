package app.fx;

import app.fx.Data.FESTIVAL_INFORMATION;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.List;

public class _env {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }

    public static int pageNumber = 1;
    public static List<FESTIVAL_INFORMATION> festival_informations;

    /**
     * 아이템 페이지 속성 초기화
     */
    public static void ResetItemProperties()
    {
        pageNumber = 1;
        festival_informations = null;
    }

    public static void main(String[] args) {
        System.out.println("DATABASE_URL: " + getEnv("DATABASE_URL"));
        System.out.println("DATABASE_ID : " + getEnv("DATABASE_ID"));
        System.out.println("DATABASE_PW : " + getEnv("DATABASE_PW"));
        System.out.println("API_KEY : " + getEnv("API_KEY"));
    }
}
