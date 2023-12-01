package fr.eql.aaitsi.project.boostEtudes.spring.controller;


import fr.eql.aaitsi.project.boostEtudes.spring.models.Education;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Role;

import fr.eql.aaitsi.project.boostEtudes.spring.repository.EducationDao;

import fr.eql.aaitsi.project.boostEtudes.spring.repository.RoleDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class EducationController {

    @Autowired
    EducationService educationService;
    private AuthenticationManager authenticationManager;
    EducationDao educationDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public EducationController(EducationService educationService, AuthenticationManager authenticationManager, EducationDao educationDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.educationService = educationService;
        this.authenticationManager = authenticationManager;
        this.educationDao = educationDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    @PostMapping("/education/register")
    public ResponseEntity<?> register(@RequestBody Education registerEducation) {
        if (educationDao.existsByUsername(registerEducation.getEmail())) {
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        Education educationMember = new Education();
        educationMember.setPassword(passwordEncoder.encode(registerEducation.getPassword()));
        educationMember.setUsername(registerEducation.getEmail());

        educationMember.setFirstname(registerEducation.getFirstname());
        educationMember.setLastname(registerEducation.getLastname());
        educationMember.setBirthdate(registerEducation.getBirthdate());
        educationMember.setAdresse(registerEducation.getAdresse());
        educationMember.setCity(registerEducation.getCity());
        educationMember.setMobile(registerEducation.getMobile());
        educationMember.setEmail(registerEducation.getEmail());
        educationMember.setRole(registerEducation.getRole());

        Role role = roleDao.findByName("EDUCATION")
                .orElseThrow(() -> new RuntimeException("Role 'EDUCATION' not found"));

        educationMember.setRoles(Collections.singletonList(role));
        educationService.saveEducationMember(educationMember);
        return new ResponseEntity<>("Username registered successfully", HttpStatus.OK);
    }






}
