package app.fx;

import io.github.cdimascio.dotenv.Dotenv;

public class _env {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }

    public static int pageNumber = 1;

    /**
     * pageNumber 초기화
     */
    public static void ResetPageNumber() { pageNumber = 1; }

    public static void main(String[] args) {
        System.out.println("DATABASE_URL: " + getEnv("DATABASE_URL"));
        System.out.println("DATABASE_ID : " + getEnv("DATABASE_ID"));
        System.out.println("DATABASE_PW : " + getEnv("DATABASE_PW"));
        System.out.println("API_KEY : " + getEnv("API_KEY"));
    }
}
