package app.fx;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getEnv(String key) {
        return dotenv.get(key);
    }

    public static void main(String[] args) {
        System.out.println("API_KEY: " + getEnv("API_KEY"));
        System.out.println("DATABASE_URL: " + getEnv("DATABASE_URL"));
    }
}
