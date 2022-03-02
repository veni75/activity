package activity;

public class ActivityWithTrack implements Activity {

    private ActivityType type;
    private Track track;

    public ActivityWithTrack(Track track, ActivityType type) {
        this.type = type;
        this.track = track;
    }

    @Override
    public double getDistance() {
        return track.getDistance();
    }

    @Override
    public ActivityType getType() {
        return type;
    }

    public Track getTrack() {
        return track;
    }
}
