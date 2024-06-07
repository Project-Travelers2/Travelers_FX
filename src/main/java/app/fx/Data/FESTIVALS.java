package app.fx.Data;

import java.util.Date;

public class FESTIVALS<T extends Data> extends Data {
    public String festival_id;
    public String festival_name;
    public String description;
    public Date start_date;
    public Date end_date;
    public String website_link;
    public String image_path;
    public String festival_code_id;
    public String local_id;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FESTIVALS [festival_id=");          builder.append(festival_id);
        builder.append(", festival_name=");                 builder.append(festival_name);
        builder.append(", description=");                   builder.append(description);
        builder.append(", start_date=");                    builder.append(start_date);
        builder.append(", end_date=");                      builder.append(end_date);
        builder.append(", website_link=");                  builder.append(website_link);
        builder.append(", image_path=");                    builder.append(image_path);
        builder.append(", festival_code_id=");              builder.append(festival_code_id);
        builder.append(", local_id=");                      builder.append(local_id);
        builder.append("]");
        return builder.toString();
    }
}
