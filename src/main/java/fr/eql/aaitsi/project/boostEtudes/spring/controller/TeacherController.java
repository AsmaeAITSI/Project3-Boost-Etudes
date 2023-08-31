package fr.eql.aaitsi.project.boostEtudes.spring.controller;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Role;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.ParentAddDto;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ParentDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.RoleDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.TeacherDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.UserDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.ParentService;
import fr.eql.aaitsi.project.boostEtudes.spring.service.TeacherService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    private AuthenticationManager authenticationManager;
    TeacherDao teacherDao;
    UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public TeacherController(TeacherService teacherService, AuthenticationManager authenticationManager, TeacherDao teacherDao, UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.teacherService = teacherService;
        this.authenticationManager = authenticationManager;
        this.teacherDao = teacherDao;
        this.userDao = userDao;
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
        teacher.setSubjects(new ArrayList<>());
        teacher.getSubjects().addAll(registerTeacher.getSubjects());

        Role role = roleDao.findByName("TEACHER").orElseThrow(() -> new RuntimeException("Role not found"));
       /* if (!entityManager.contains(role)) {
            // If the role is detached, merge it within a transaction
            role = entityManager.merge(role);
        }*/
        teacher.setRoles(Collections.singletonList(role));
        teacherService.saveTeacher(teacher);
        return new ResponseEntity<>("Username registered successfully", HttpStatus.OK);
    }






/*    @GetMapping(path = "/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {

        List<Teacher> teachers = teacherService.getAllTeacher();

        return new ResponseEntity<>(teachers, HttpStatus.OK); // renvoie vers le front
    }

    @GetMapping(path = "/teachers/{teacherId}")
    public ResponseEntity<Teacher> getTeacher (@PathVariable("teacherId") Long id) {

        Teacher foundTeacher = teacherService.getTeacherById(id);

        return new ResponseEntity<>(foundTeacher, HttpStatus.OK);

    }*/
}
