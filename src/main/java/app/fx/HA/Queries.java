package app.fx.HA;

import app.fx.Data.AIRPORT_INFORMATION;
import app.fx.Data.FESTIVALS;
import app.fx.Data.USERS;
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

    public static void main(String[] args) {
        Queries.instance.all_festival_list();
    }

    public List<FESTIVALS> all_festival_list() {
        List<FESTIVALS> festival_list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, id, pw)){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from FESTIVALS");

            while(rs.next()) {
                FESTIVALS f = new FESTIVALS();
                f.festival_id = rs.getString("FESTIVAL_ID");
                f.festival_name = rs.getString("FESTIVAL_NAME");
                f.description = rs.getString("DESCRIPTION");
                f.start_date = rs.getDate("START_DATE");
                f.end_date = rs.getDate("END_DATE");
                f.website_link = rs.getString("WEBSITE_LINK");
                f.image_path = rs.getString("IMAGE_PATH");
                f.festival_code_id = rs.getString("FESTIVAL_CODE_ID");
                f.local_id = rs.getString("LOCAL_ID");

                festival_list.add(f);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return festival_list;
        }
    }

    public List<FESTIVALS> specific_festival_list(int festival_id) {
        List<FESTIVALS> festival_list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, id, pw)){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from FESTIVALS where FESTIVAL_CODE_ID = " + festival_id);

            while(rs.next()) {
                FESTIVALS f = new FESTIVALS();
                f.festival_id = rs.getString("FESTIVAL_ID");
                f.festival_name = rs.getString("FESTIVAL_NAME");
                f.description = rs.getString("DESCRIPTION");
                f.start_date = rs.getDate("START_DATE");
                f.end_date = rs.getDate("END_DATE");
                f.website_link = rs.getString("WEBSITE_LINK");
                f.image_path = rs.getString("IMAGE_PATH");
                f.festival_code_id = rs.getString("FESTIVAL_CODE_ID");
                f.local_id = rs.getString("LOCAL_ID");

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
            ResultSet rs = st.executeQuery("SELECT * FROM AIRPORTS WHERE ISO_COUNTRY = " + "\'"+CountryCode+"\'"+
                    "AND SCHEDULED_SERVICE = " + "\'yes\'");

            while(rs.next()) {
                AIRPORT_INFORMATION air = new AIRPORT_INFORMATION();
                air.airport_id = rs.getString("AIRPORT_ID");
                air.type = rs.getString("TYPE");
                air.name = rs.getString("NAME");
                air.latitude_deg = rs.getInt("LATITUDE_DEG");
                air.longitude_deg = rs.getInt("LONGITUDE_DEG");
                air.municipality = rs.getString("MUNICIPALITY");
                air.scheduled_service = rs.getString("SCHEDULED_SERVICE");
                air.iso_country = rs.getString("ISO_COUNTRY");

                System.out.println(air.toString());

                airport_list.add(air);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return airport_list;
        }
    }

    public boolean validateLogin(String userId, String userpw) {
        boolean isValid = false;
        try (Connection conn = DriverManager.getConnection(url, id, pw)) {
            String query = "SELECT * FROM USERS WHERE USER_NAME = ? AND USER_PASSWORD = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, userpw);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                USERS user = new USERS();
                user.user_id = resultSet.getInt("USER_ID");
                user.user_name = resultSet.getString("USER_NAME");
                user.user_password = resultSet.getString("USER_PASSWORD");
                user.user_type = String.valueOf(resultSet.getInt("USER_TYPE"));

                _env.selected_user = user;
                isValid = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValid;
    }
}
