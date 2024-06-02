package app.fx.HA;

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
}
