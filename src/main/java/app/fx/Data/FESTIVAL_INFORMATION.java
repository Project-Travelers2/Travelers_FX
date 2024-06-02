package app.fx.Data;

public class FESTIVAL_INFORMATION<T extends Data> extends Data {
    public int festival_loccode;
    public int festival_code;
    public int festival_mainid;
    public String festival_name;
    public String location;
    public String date_range;
    public String description;
    public String website_link;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FESTIVAL_INFORMATION [festival_loccode=");        builder.append(festival_loccode);
        builder.append(", festival_code=\t");        builder.append(festival_code);
        builder.append(", festival_mainid=\t");      builder.append(festival_mainid);
        builder.append(", festival_name=\t");        builder.append(festival_name);
        builder.append(", location=\t");             builder.append(location);
        builder.append(", date_range=\t");           builder.append(date_range);
        builder.append(", description=\t");          builder.append(description);
        builder.append(", website_link=\t");         builder.append(website_link);
        builder.append("]");
        return builder.toString();
    }
}
