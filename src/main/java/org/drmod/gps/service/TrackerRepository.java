package org.drmod.gps.service;

import org.drmod.gps.domain.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackerRepository extends JpaRepository<Tracker, Long> {
    Optional<Tracker> findTopById(int id);
}
