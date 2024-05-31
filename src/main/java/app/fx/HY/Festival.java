package app.fx.HY;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Festival {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");


            String url = "jdbc:oracle:thin:@localhost:1521/xe";
            String sql = "select * from festival_information";
            String id = "festival";
            String pw = "oracle_4U";
            Connection conn = DriverManager.getConnection(url, id, pw);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                int f_num = rs.getInt("festival_mainid");
                String f_name = rs.getString("festival_name");
                System.out.println(f_num+ "\t" +f_name);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }finally {

        }

    }
}
