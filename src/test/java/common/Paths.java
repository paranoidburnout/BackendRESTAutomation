package common;

public class Paths {

    public static final String AUTHENTICATION = "/auth";
    public static final String BOOKING = "/booking";
    public static final String BOOKING_ID = "/booking/";
    public static final String DELETE = "/booking/";
    public static final String PING = "/ping";

    public String path;

    Paths(String path) {
        this.path = path;
    }
}
