package org.drmod.gps.controller;

import org.drmod.gps.domain.Coordinate;
import org.drmod.gps.service.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/client/coordinate/")
@RestController
public class ClientTrackerController {

    @Autowired
    CoordinateRepository coordinateRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void addCoordinate(@RequestBody Coordinate coordinate){
        coordinateRepository.save(coordinate);
    }

}
