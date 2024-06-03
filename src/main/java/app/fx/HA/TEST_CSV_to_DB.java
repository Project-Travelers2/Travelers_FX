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
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            for (CSVRecord csvRecord : csvParser) {
//                statement.setString(1, csvRecord.get("id"));
                System.out.print(csvRecord.get("id") + "\t");
                System.out.print(csvRecord.get("ident") + "\t");
                System.out.print(csvRecord.get("type") + "\t");
                System.out.print(csvRecord.get("name") + "\t");
                System.out.print(csvRecord.get("latitude_deg") + "\t");
                System.out.print(csvRecord.get("longitude_deg") + "\t");
                System.out.print(csvRecord.get("elevation_ft") + "\t");
                System.out.print(csvRecord.get("continent") + "\t");
                System.out.print(csvRecord.get("iso_country") + "\t");
                System.out.print(csvRecord.get("iso_region") + "\t");
                System.out.print(csvRecord.get("municipality") + "\t");
                System.out.print(csvRecord.get("scheduled_service") + "\t");
                System.out.print(csvRecord.get("gps_code") + "\t");
                System.out.print(csvRecord.get("iata_code") + "\t");
                System.out.print(csvRecord.get("local_code") + "\t");
                System.out.print(csvRecord.get("home_link") + "\t");
                System.out.print(csvRecord.get("wikipedia_link") + "\t");
                System.out.print(csvRecord.get("keywords") + "\t");
                System.out.println();
            }

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
