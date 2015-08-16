package org.drmod.gps.util;

import org.drmod.gps.exception.NoSuchTrackerException;
import org.springframework.web.bind.annotation.PathVariable;

public class ControllerUtils {

    public static int parseTrackerId(@PathVariable String id) {
        int trackerId;
        try {
            trackerId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            throw new NoSuchTrackerException();
        }
        return trackerId;
    }

}
