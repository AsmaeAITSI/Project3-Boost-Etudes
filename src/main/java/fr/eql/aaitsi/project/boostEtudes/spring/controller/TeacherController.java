package fr.eql.aaitsi.project.boostEtudes.spring.controller;

import com.sun.istack.logging.Logger;
import fr.eql.aaitsi.project.boostEtudes.spring.exception.UserException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Role;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Subject;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityDto;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.TeacherDto;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.RoleDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.SubjectDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.TeacherDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.UserDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.TeacherService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("${front.url}")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    private AuthenticationManager authenticationManager;
    TeacherDao teacherDao;
    UserDao userDao;
    SubjectDao subjectDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;


    public TeacherController(TeacherService teacherService, AuthenticationManager authenticationManager, TeacherDao teacherDao, UserDao userDao, SubjectDao subjectDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.teacherService = teacherService;
        this.authenticationManager = authenticationManager;
        this.teacherDao = teacherDao;
        this.userDao = userDao;
        this.subjectDao = subjectDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional
    @PostMapping("/teacher/register")
    public ResponseEntity<?> register(@RequestBody Teacher registerTeacher) {
        if (userDao.existsByUsername(registerTeacher.getEmail())) {
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        Teacher teacher = new Teacher();
        teacher.setPassword(passwordEncoder.encode(registerTeacher.getPassword()));
        teacher.setUsername(registerTeacher.getEmail());

        teacher.setFirstname(registerTeacher.getFirstname());
        teacher.setLastname(registerTeacher.getLastname());
        teacher.setBirthdate(registerTeacher.getBirthdate());
        teacher.setAdresse(registerTeacher.getAdresse());
        teacher.setCity(registerTeacher.getCity());
        teacher.setMobile(registerTeacher.getMobile());
        teacher.setEmail(registerTeacher.getEmail());
        teacher.setProfession(registerTeacher.getProfession());
        /*teacher.setSubjects(new ArrayList<>());
        teacher.getSubjects().addAll(registerTeacher.getSubjects());*/
        // Check and add existing subjects or create new ones
        /*Set<Subject> uniqueSubjects = new HashSet<>();*/
        List<Subject> uniqueSubjects = new ArrayList<>();

        // Pour chaque matière dans les matières fournies
        for (Subject subject : registerTeacher.getSubjects()) {
            System.out.println("test");
            // Vérifier si la matière existe déjà dans la base de données
            Optional<Subject> existingSubjectOptional = subjectDao.findByName(subject.getName());
            Subject existingSubject = existingSubjectOptional.orElse(new Subject(subject.getName()));
            // Ajouter la matière à la liste des matières uniques
            uniqueSubjects.add(existingSubject);
        }

        // Définir les matières uniques pour l'enseignant
        teacher.setSubjects(uniqueSubjects);

        Role role = roleDao.findByName("TEACHER").orElseThrow(() -> new RuntimeException("Role not found"));
       /* if (!entityManager.contains(role)) {
            // If the role is detached, merge it within a transaction
            role = entityManager.merge(role);
        }*/
        teacher.setRoles(Collections.singletonList(role));
        teacherService.saveTeacher(teacher);
        return new ResponseEntity<>("Username registered successfully", HttpStatus.OK);
    }



    @GetMapping(path = "/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {

        List<Teacher> teachers = teacherService.getAllTeacher();

        return new ResponseEntity<>(teachers, HttpStatus.OK); // renvoie vers le front
    }

    @Transactional
    @GetMapping(path = "/teachers/{teacherId}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long teacherId) {
        Teacher teacher = teacherService.getTeacherWithSubjectsById(teacherId);
        if (teacher != null) {
            TeacherDto teacherDTO = teacherService.mapTeacherToDTO(teacher);
            return new ResponseEntity<>(teacherDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updatet/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable ("teacherId") Long id, @RequestBody Teacher updatedTeacher) {
        try {
            Teacher teacher = teacherService.updateTeacher(id, updatedTeacher);
            return ResponseEntity.ok(teacher);
        } catch (UserException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{teacherId}/availability")
    public ResponseEntity<String> addAvailabilityToTeacher(
            @PathVariable Long teacherId,
            @RequestBody Availability newAvailability) {

        try {
            Teacher teacher = teacherDao.findById(teacherId)
                    .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + teacherId));

            Availability availability = new Availability();
            availability.setDay(newAvailability.getDay());
            availability.setStartTime(newAvailability.getStartTime());
            availability.setEndTime(newAvailability.getEndTime());



            teacherService.addAvailability(teacherId, availability);
            teacherDao.save(teacher);

            return new ResponseEntity<>("Availability added successfully", HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("Teacher not found with ID: " + teacherId, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding availability", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Transactional
    @GetMapping("/{teacherId}/availabilities")
    public ResponseEntity<List<AvailabilityDto>> getAvailabilitiesByTeacher(@PathVariable Long teacherId) {
        try {
            List<AvailabilityDto> availabilities = teacherService.getAvailabilitiesByTeacherId(teacherId);
            return ResponseEntity.ok(availabilities);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
