package activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Activities {

    private List<Activity> activities;

    public Activities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public List<Report> distancesByTypes() {
        List<Report> result = new ArrayList<>();
        for (Activity a: activities) {
            if (a instanceof ActivityWithTrack) {
                ActivityWithTrack at = (ActivityWithTrack) a;
                result.add(new Report(at.getType(), at.getDistance() * 2));
            }
            if (a instanceof ActivityWithoutTrack) {
               ActivityWithoutTrack aw = (ActivityWithoutTrack) a;
               result.add(new Report(aw.getType(), aw.getDistance()));
            }
        }
        return result;
    }

    public int numberOfTrackActivities() {
        int counter = 0;
        for (Activity a: activities) {
            if (a instanceof ActivityWithTrack) {
                counter++;
            }
        }
        return counter;
    }

    public int numberOfWithoutTrackActivities() {
        int counter = 0;
        for (Activity a: activities) {
            if (a instanceof ActivityWithoutTrack) {
                counter++;
            }
        }
        return counter;
    }

}
