package app.fx.HA;

import app.fx._env;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TEST_CSV_to_DB {


    public static void main(String[] args) {
        String csvFilePath = "C:\\Projects/airports.csv";
        String jdbcURL = _env.getEnv("DATABASE_URL");
        String username = _env.getEnv("DATABASE_ID");
        String password = _env.getEnv("DATABASE_PW");

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            FileReader reader = new FileReader(csvFilePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());

            String sql = "INSERT INTO airports " +
                    "(id, ident, name, latitude_deg, longitude_deg, elevation_ft, " +
                    "continent, iso_country, iso_region, municipality, scheduled_service," +
                    "gps_code, iata_code, local_code, home_link, wikipedia_link, keywords) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            for (CSVRecord csvRecord : csvParser) {
                System.out.print(csvRecord.get("id") + "\t");
                statement.setString(1, csvRecord.get("id"));
                System.out.print(csvRecord.get("ident") + "\t");
                statement.setString(2, csvRecord.get("ident"));
                System.out.print(csvRecord.get("type") + "\t");
                statement.setString(3, csvRecord.get("type"));
                System.out.print(csvRecord.get("name") + "\t");
                statement.setString(4, csvRecord.get("name"));
                System.out.print(csvRecord.get("latitude_deg") + "\t");
                statement.setString(5, csvRecord.get("latitude_deg"));
                System.out.print(csvRecord.get("longitude_deg") + "\t");
                statement.setString(6, csvRecord.get("longitude_deg"));
                System.out.print(csvRecord.get("elevation_ft") + "\t");
                statement.setString(7, csvRecord.get("elevation_ft"));
                System.out.print(csvRecord.get("continent") + "\t");
                statement.setString(8, csvRecord.get("continent"));
                System.out.print(csvRecord.get("iso_country") + "\t");
                statement.setString(9, csvRecord.get("iso_region") + "\t");
                System.out.print(csvRecord.get("iso_region") + "\t");
                statement.setString(10, csvRecord.get("municipality") + "\t");
                System.out.print(csvRecord.get("municipality") + "\t");
                statement.setString(11, csvRecord.get("scheduled_service") + "\t");
                System.out.print(csvRecord.get("scheduled_service") + "\t");
                statement.setString(12, csvRecord.get("gps_code") + "\t");
                System.out.print(csvRecord.get("gps_code") + "\t");
                statement.setString(13, csvRecord.get("keywords") + "\t");
                System.out.print(csvRecord.get("iata_code") + "\t");
                statement.setString(14, csvRecord.get("local_code") + "\t");
                System.out.print(csvRecord.get("local_code") + "\t");
                statement.setString(15, csvRecord.get("home_link") + "\t");
                System.out.print(csvRecord.get("home_link") + "\t");
                statement.setString(16, csvRecord.get("wikipedia_link") + "\t");
                System.out.print(csvRecord.get("wikipedia_link") + "\t");
                statement.setString(17, csvRecord.get("keywords") + "\t");
                System.out.print(csvRecord.get("keywords") + "\t");
                statement.setString(18, csvRecord.get("keywords") + "\t");
                statement.addBatch();

                System.out.println();
            }

            statement.executeBatch();
            connection.commit();

            csvParser.close();
            reader.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
