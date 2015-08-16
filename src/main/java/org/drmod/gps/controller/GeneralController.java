package org.drmod.gps.controller;

import org.drmod.gps.domain.Coordinate;
import org.drmod.gps.domain.Tracker;
import org.drmod.gps.exception.NoSuchTrackerException;
import org.drmod.gps.service.CoordinateRepository;
import org.drmod.gps.service.TrackerRepository;
import org.drmod.gps.util.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;

@RestController
public class GeneralController {

    @Autowired
    TrackerRepository trackerRepository;

    @Autowired
    CoordinateRepository coordinateRepository;

    @RequestMapping("/resource/{id}")
    public Map<String,Object> home(@PathVariable String id) {

//        Tracker trackerFake = trackerRepository.findTopById(1).orElse(new Tracker());
//        trackerFake.setName("first");
//        trackerRepository.save(trackerFake);
//
//
//        Coordinate coordinateFake = new Coordinate();
//        coordinateFake.setTracker(trackerFake);
//        coordinateFake.setLatitude(new Random().nextDouble());
//        coordinateFake.setLongitude(new Random().nextDouble());
//        coordinateRepository.save(coordinateFake);


        int trackerId = ControllerUtils.parseTrackerId(id);
        Tracker tracker = trackerRepository.findTopById(trackerId).orElseThrow(NoSuchTrackerException::new);
        Coordinate coordinate = coordinateRepository.findTopByTrackerOrderByCreatedDesc(tracker).
                orElseThrow(NoSuchTrackerException::new);

        Map<String,Object> model = new HashMap<>();
        model.put("coordinate", coordinate);
        return model;
    }


    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
