package activity;

public class Coordinate {

    private final double latitude; //szélesség
    private final double longitude;

    public Coordinate(double latitude, double longitude) {
        if (latitude > 90 || latitude < -90) {
            throw new IllegalArgumentException("Latitude is invalid value!");
        }
        if (longitude > 180 || longitude < -180) {
            throw new IllegalArgumentException("Longitude is invalid value!");
        }
            this.latitude = latitude;
            this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
