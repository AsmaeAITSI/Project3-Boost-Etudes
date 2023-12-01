package fr.eql.aaitsi.project.boostEtudes.spring.controller;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Subject;
import fr.eql.aaitsi.project.boostEtudes.spring.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("${front.url}")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

   
    @GetMapping(path="/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
}
