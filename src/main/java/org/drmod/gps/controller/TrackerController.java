package org.drmod.gps.controller;

import org.drmod.gps.domain.Tracker;
import org.drmod.gps.exception.NoSuchTrackerException;
import org.drmod.gps.service.TrackerRepository;
import org.drmod.gps.util.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/tracker/")
@RestController
public class TrackerController {

    @Autowired
    TrackerRepository repository;

    @RequestMapping
    public Map<String, Object> getAllTrackers(){
        Map<String,Object> model = new HashMap<>();
        model.put("trackers", repository.findAll());
        return model;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteTracker(@PathVariable String id){
        int trackerId = ControllerUtils.parseTrackerId(id);
        Optional<Tracker> oneById = repository.findTopById(trackerId);
        Tracker tracker = oneById.orElseThrow(NoSuchTrackerException::new);
        repository.delete(tracker);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void addTracker(@RequestBody Tracker tracker){
        repository.save(tracker);
    }
}
