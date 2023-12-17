package fr.eql.aaitsi.project.boostEtudes.spring.controller;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Course;

import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.CourseResponseDto;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.AvailabilityDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.AvailabilityService;
import fr.eql.aaitsi.project.boostEtudes.spring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@RestController
@RequestMapping("/api/auth/courses")
@CrossOrigin("${front.url}")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private AvailabilityDao availabilityDao;


    @Autowired
    private AvailabilityService availabilityService;

    @PostMapping("/{parentId}/{availabilityId}/create")
    public ResponseEntity<Course> createCourse(
            @PathVariable Long parentId,
            @PathVariable Long availabilityId) {

        // Récupérer l'objet Availability à partir de l'AvailabilityService
        Availability availability = availabilityDao.findById(availabilityId)
                .orElseThrow(() -> new EntityNotFoundException("Availability not found"));

        // Créer le cours à partir du CourseService
        Course createdCourse = courseService.createCourse(availabilityId, parentId);

        // Supprimer les autres disponibilités non réservées du même prof avec la même date et heure
        availabilityService.deleteOtherUnreservedAvailabilities(availability.getTeacher().getUserId(), availability.getDay(), availability.getStartTime(), availabilityId);

        // Retourner le cours créé en réponse
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }


    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<CourseResponseDto>> getCoursesByParent(@PathVariable Long parentId) {
        List<CourseResponseDto> courses = courseService.getCoursesByParent(parentId);
        return ResponseEntity.ok(courses);
    }
}
