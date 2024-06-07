package app.fx.Data;

public class AIRPORT_INFORMATION {
    public String airport_id;
    public String type;
    public String name;
    public int latitude_deg;
    public int longitude_deg;
    public String municipality;
    public String scheduled_service;
    public String iso_country;

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
