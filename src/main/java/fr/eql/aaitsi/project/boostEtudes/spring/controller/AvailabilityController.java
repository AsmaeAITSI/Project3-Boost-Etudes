package fr.eql.aaitsi.project.boostEtudes.spring.controller;


import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityProjection;
import fr.eql.aaitsi.project.boostEtudes.spring.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/availabilities")
@CrossOrigin("${front.url}")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    // Endpoint pour récupérer toutes les disponibilités des professeurs
    /*@Transactional
    @GetMapping(path = "/all")
    public ResponseEntity<List<AvailabilityDto>> getAllAvailabilities() {

        List<AvailabilityDto> availabilities = availabilityService.getAllAvailabilities();
        return new ResponseEntity<>(availabilities, HttpStatus.OK);
    }*/

    @Transactional
    @GetMapping(path = "/all")
    public List<AvailabilityProjection> getAvailabilitiesSortedByTeacher() {
        return availabilityService.getAllAvailabilitiesWithTeacherInfo();
    }
}
