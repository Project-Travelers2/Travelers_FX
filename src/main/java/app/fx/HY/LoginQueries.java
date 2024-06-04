package app.fx.HY;

import app.fx.Data.USERS;
import app.fx._env;

import java.sql.*;
import java.util.List;

public class LoginQueries {
    public static final LoginQueries instance = new LoginQueries();

    private String url, id, pw;

    private LoginQueries() {
        this.url = _env.getEnv("DATABASE_URL");
        this.id = _env.getEnv("DATABASE_ID");
        this.pw = _env.getEnv("DATABASE_PW");
    }

    public List<USERS> all_user_list() {
        List<USERS> User_list = (List<USERS>) new USERS<>();

        try (Connection conn = DriverManager.getConnection(url, id, pw)){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from USERS");

            while(rs.next()) {
                USERS users = new USERS();

                users.user_id = rs.getInt("USER_ID");
                users.user_name = rs.getString("USER_NAME");
                users.user_password = rs.getString("USER_PASSWORD");
                users.user_type = rs.getString("USER_TYPE");


                User_list.add(users);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return User_list;
        }
    }
}
