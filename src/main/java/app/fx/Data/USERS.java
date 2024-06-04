package app.fx.Data;

public class USERS <T extends Data> extends Data {
    public int user_id;
    public String user_name;
    public String user_password;
    public String user_type;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("USERS [=");        builder.append(user_id);
        builder.append(", USER_NAME\t");        builder.append(user_name);
        builder.append(", USER_PASSWORD=\t");      builder.append(user_password);
        builder.append(", USER_TYPE=\t");      builder.append(user_type);
        builder.append("]");
        return builder.toString();
    }
}
