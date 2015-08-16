package org.drmod.gps.service;

import org.drmod.gps.domain.Coordinate;
import org.drmod.gps.domain.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    Optional<Coordinate> findTopById(int id);

    Optional<Coordinate> findTopByTrackerOrderByCreatedDesc(Tracker tracker);
}
