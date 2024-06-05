package app.fx.HY;

import app.fx._env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Festival {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");


            String url = _env.getEnv("DATABASE_URL");
            String sql = _env.getEnv("Login_INFO");
//            String sql2 = _env.getEnv("QUERY_ALL_INFO");
            String id = _env.getEnv("DATABASE_ID");
            String pw = _env.getEnv("DATABASE_PW");
            Connection conn = DriverManager.getConnection(url, id, pw);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

//            while(rs.next()) {
//                int f_num = rs.getInt("festival_mainid");
//                String f_name = rs.getString("festival_name");
//                System.out.println(f_num+ "\t" +f_name);
//            }

            while(rs.next()) {
                String user_id = rs.getString("USER_ID");
                String user_name = rs.getString("USER_NAME");
                String user_pw = rs.getString("USER_PASSWORD");
                String user_type = rs.getString("USER_TYPE");
                System.out.println(user_id + " " + user_name + " " + user_pw + " " + user_type);
            }

//            rs = st.executeQuery(sql2);
//            while(rs.next()) {
//                int f_num = rs.getInt("festival_mainid");
//                String f_name = rs.getString("festival_name");
//                System.out.println(f_num+ "\t" +f_name);
//            }

        } catch(Exception e) {
            e.printStackTrace();
        }finally {

        }

    }
}
