package app.fx.HA;

import app.fx.EnvConfig;

import java.sql.*;

import app.fx.EnvConfig;
import org.jetbrains.annotations.NotNull;

public class Queries {
    public static final Queries instance = new Queries();

    private String url, id, pw;

    private Queries() {
        this.url = EnvConfig.getEnv("DATABASE_URL");
        this.id = EnvConfig.getEnv("DATABASE_ID");
        this.pw = EnvConfig.getEnv("DATABASE_PW");
    }

    public void all_festival_list() {
        try (Connection conn = DriverManager.getConnection(url, id, pw)){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from festival_information");

            while(rs.next()) {
                int f_num = rs.getInt("festival_mainid");
                String f_name = rs.getString("festival_name");
//                System.out.println(rs.getRow());
                System.out.println(f_num+ "\t" +f_name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void specific_festival_list(int festival_id) {
        try (Connection conn = DriverManager.getConnection(url, id, pw)){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from festival_information where FESTIVAL_CODE = " + festival_id);

            while(rs.next()) {
                int f_num = rs.getInt("festival_mainid");
                String f_name = rs.getString("festival_name");
//                System.out.println(rs.getRow());
                System.out.println(f_num+ "\t" +f_name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
