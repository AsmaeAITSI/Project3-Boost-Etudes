package fr.eql.aaitsi.project.boostEtudes.spring.controller;

import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.CourseRequestDto;
import fr.eql.aaitsi.project.boostEtudes.spring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("${front.url}")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/courses/add")
    public ResponseEntity<String> createCourse(@RequestBody CourseRequestDto courseRequest) {
        try {
            courseService.createCourse(courseRequest);
            return new ResponseEntity<>("Course created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the course", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
