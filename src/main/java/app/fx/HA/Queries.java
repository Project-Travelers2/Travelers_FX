package app.fx.HA;

import app.fx.Data.AIRPORT_INFORMATION;
import app.fx.Data.FESTIVAL_INFORMATION;
import app.fx._env;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Queries {
    public static final Queries instance = new Queries();

    private String url, id, pw;

    private Queries() {
        this.url = _env.getEnv("DATABASE_URL");
        this.id = _env.getEnv("DATABASE_ID");
        this.pw = _env.getEnv("DATABASE_PW");
    }

    public List<FESTIVAL_INFORMATION> all_festival_list() {
        List<FESTIVAL_INFORMATION> festival_list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, id, pw)){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from festival_information");

            while(rs.next()) {
                FESTIVAL_INFORMATION f = new FESTIVAL_INFORMATION();

                f.festival_loccode = rs.getInt("FESTIVAL_LOCCODE");
                f.festival_code = rs.getInt("FESTIVAL_CODE");
                f.festival_mainid = rs.getInt("FESTIVAL_MAINID");
                f.festival_name = rs.getString("FESTIVAL_NAME");
                f.location = rs.getString("LOCATION");
                f.date_range = rs.getString("DATE_RANGE");
                f.description = rs.getString("DESCRIPTION");
                f.website_link = rs.getString("WEBSITE_LINK");

                festival_list.add(f);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return festival_list;
        }
    }

    public List<FESTIVAL_INFORMATION> specific_festival_list(int festival_id) {
        List<FESTIVAL_INFORMATION> festival_list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, id, pw)){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from festival_information where FESTIVAL_CODE = " + festival_id);

            while(rs.next()) {
                FESTIVAL_INFORMATION f = new FESTIVAL_INFORMATION();

                f.festival_loccode = rs.getInt("FESTIVAL_LOCCODE");
                f.festival_code = rs.getInt("FESTIVAL_CODE");
                f.festival_mainid = rs.getInt("FESTIVAL_MAINID");
                f.festival_name = rs.getString("FESTIVAL_NAME");
                f.location = rs.getString("LOCATION");
                f.date_range = rs.getString("DATE_RANGE");
                f.description = rs.getString("DESCRIPTION");
                f.website_link = rs.getString("WEBSITE_LINK");

                festival_list.add(f);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return festival_list;
        }
    }

    public List<AIRPORT_INFORMATION> airport_list(String CountryCode) {
        List<AIRPORT_INFORMATION> airport_list = new ArrayList<>();
        System.out.println("Country code: " + CountryCode);

        try (Connection conn = DriverManager.getConnection(url, id, pw)) {
            Statement st = conn.createStatement();
            // 컬럼명은 대소문자 구분합니다. ㅂㄷㅂㄷ
//            ResultSet rs = st.executeQuery("select * from AIRPORTS WHERE COUNTRY_CODE = " + CountryCode);
            ResultSet rs = st.executeQuery("SELECT * FROM airports WHERE iso_country = " + "\'"+CountryCode+"\'"+
                    "AND SCHEDULED_SERVICE = " + "\'yes\'");

            while(rs.next()) {
                AIRPORT_INFORMATION air = new AIRPORT_INFORMATION();
                air.id = rs.getString("id");
                air.ident = rs.getString("ident");
                air.type = rs.getString("type");
                air.name = rs.getString("name");
                air.latitude_deg = rs.getInt("latitude_deg");
                air.longitude_deg = rs.getInt("longitude_deg");
                air.elevation_ft = rs.getInt("elevation_ft");
                air.continent = rs.getString("continent");
                air.iso_country = rs.getString("iso_country");
                air.iso_region = rs.getString("iso_region");
                air.municipality = rs.getString("municipality");
                air.scheduled_service = rs.getString("scheduled_service");
                air.gps_code = rs.getString("gps_code");
                air.iata_code = rs.getString("iata_code");
                air.local_code = rs.getString("local_code");
                air.home_link = rs.getString("home_link");
                air.wikipedia_link = rs.getString("wikipedia_link");
                air.keywords = rs.getString("keywords");

                System.out.println(air.toString());

                airport_list.add(air);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return airport_list;
        }
    }
}
