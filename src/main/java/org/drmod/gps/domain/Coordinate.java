package org.drmod.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Coordinate {

    @Id
    @GeneratedValue
    private int id;

    private Date created;

    private double latitude;

    private double longitude;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "tracker_id")
    @ElementCollection(targetClass = Tracker.class)
    private Tracker tracker;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

