package activity;

import java.util.Objects;

public class TrackPoint {

    private Coordinate coordinate;
    private double elevation;  //emelkedés

    public TrackPoint(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public TrackPoint(Coordinate coordinate, double elevation) {
        this.coordinate = coordinate;
        this.elevation = elevation;
    }

    public double getDistanceFrom(TrackPoint point) {
        final int REarth = 6371;
        double latDistance = Math.toRadians(point.getCoordinate().getLatitude() - this.coordinate.getLatitude());
        double lonDistance = Math.toRadians(point.getCoordinate().getLongitude() - this.coordinate.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(this.coordinate.getLatitude()))
                * Math.cos(Math.toRadians(point.getCoordinate().getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = REarth * c * 1000;
        double height = this.elevation - point.getElevation();
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        return Math.sqrt(distance);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getElevation() {
        return elevation;
    }

    @Override
    public String toString() {
        return "TrackPoint{" +
                "coordinate=" + coordinate +
                ", elevation=" + elevation +
                '}';
    }
}
