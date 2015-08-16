package org.drmod.gps.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tracker {

    @Id
    @GeneratedValue
    @Column(name = "tracker_id")
    private int id;

    //@Column(unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tracker")
    private Set<Coordinate> coordinates = new HashSet<>();

    public int getId() {
        return id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Set<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }
}
