package activity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Track {

    private List<TrackPoint> trackPoints = new ArrayList<>();

    public void addTrackPoint(TrackPoint point) {
        trackPoints.add(point);
    }

    public Coordinate findMaximumCoordinate() {
        double coordinateLatitude = trackPoints.stream().mapToDouble(t -> t.getCoordinate().getLatitude()).max().getAsDouble();
        double coordinateLongitude = trackPoints.stream().mapToDouble(t -> t.getCoordinate().getLongitude()).max().getAsDouble();
        return new Coordinate(coordinateLatitude, coordinateLongitude);
    }

    public Coordinate findMinimumCoordinate() {
        double coordinateLatitude = trackPoints.stream().mapToDouble(t -> t.getCoordinate().getLatitude()).min().getAsDouble();
        double coordinateLongitude = trackPoints.stream().mapToDouble(t -> t.getCoordinate().getLongitude()).min().getAsDouble();
        return new Coordinate(coordinateLatitude, coordinateLongitude);
    }

    public double getDistance() {
        double sum = 0;
        for (int i = 0; i < trackPoints.size()-1; i++) {
            double distance = trackPoints.get(i).getDistanceFrom(trackPoints.get(i+1));
            sum += distance;
        }
        return sum;
    }

    public double getFullDecrease() {
        double sum = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            double deltaElevation = trackPoints.get(i).getElevation() - trackPoints.get(i-1).getElevation();
            if ((deltaElevation) < 0) {
                sum += deltaElevation;
            }
        }
        return Math.abs(sum);
    }

    public double getFullElevation() {
        double sum = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            double deltaElevation = trackPoints.get(i).getElevation() - trackPoints.get(i-1).getElevation();
            if ((deltaElevation) > 0) {
                sum += deltaElevation;
            }
        }
        return sum;
    }

    public double getRectangleArea() {
        double distance12 = trackPoints.get(0).getDistanceFrom(trackPoints.get(1));
        double distance13 = trackPoints.get(0).getDistanceFrom(trackPoints.get(2));
        double result = distance12 * distance13;
        return result;
    }

    public List<TrackPoint> getTrackPoints() {
        return new ArrayList<>(trackPoints);
    }

    public void loadFromGpx(Path path) {
        List<String> readTrack = readFile(path);
        double elevation = 0;
        for (int i = 10; i < readTrack.size(); i++) {
            String[] stSplit = readTrack.get(i-1).trim().split(" ");
            String[] stSplitElevation = readTrack.get(i).trim().split(" ");

            if (stSplitElevation[0].startsWith("<ele")) {
                elevation = Double.parseDouble(stSplitElevation[0].substring(5, 10));
            }

            if (stSplit[0].equals("<trkpt")) {
                double latitude = Double.parseDouble(stSplit[1].substring(5, 15));
                double longitude = Double.parseDouble(stSplit[2].substring(5, 15));
                Coordinate coordinate = new Coordinate(latitude, longitude);
                addTrackPoint(new TrackPoint(coordinate, elevation));
            }
        }
    }

    private List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        }
        catch (IOException ioe) {
            throw new IllegalArgumentException("Can't read file!", ioe);
        }
    }
}
