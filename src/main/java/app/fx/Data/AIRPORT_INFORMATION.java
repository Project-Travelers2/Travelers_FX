package app.fx.Data;

public class AIRPORT_INFORMATION {
    public String id;
    public String ident;
    public String type;
    public String name;
    public int latitude_deg;
    public int longitude_deg;
    public int elevation_ft;
    public String continent;
    public String iso_country;
    public String iso_region;
    public String municipality;
    public String scheduled_service;
    public String gps_code;
    public String iata_code;
    public String local_code;
    public String home_link;
    public String wikipedia_link;
    public String keywords;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return iso_country + ": " + name;
    }

//    @Override
//    public String toString() {
//        return "AIRPORT_INFORMATION [id=" + id + ", ident=" + ident
//                + ", type=" + type + ", name=" + name + ", latitude_deg="
//                + latitude_deg + ", longitude_deg=" + longitude_deg
//                + ", elevation_ft=" + elevation_ft
//                + ", continent=" + continent + ", iso_country="
//                + iso_country + ", iso_region=" + iso_region
//                + ", municipality=" + municipality + ", scheduled_service="
//                + scheduled_service + ", gps_code=" + gps_code
//                + ", iata_code=" + iata_code + ", local_code="
//                + local_code + ", home_link=" + home_link
//                + ", wikipedia_link=" + wikipedia_link
//                + ", keywords=" + keywords + "]";
//
//    }
}
