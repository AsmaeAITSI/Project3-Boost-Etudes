package fr.eql.aaitsi.project.boostEtudes.spring.controller;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Role;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.ParentAddDto;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ParentDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.RoleDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.ParentService;
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
public class ParentController {

    @Autowired
    ParentService parentService;
    private AuthenticationManager authenticationManager;
    ParentDao parentDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;


    public ParentController(ParentService parentService, AuthenticationManager authenticationManager, ParentDao parentDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.parentService = parentService;
        this.authenticationManager = authenticationManager;
        this.parentDao = parentDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping("/parent/register")
    public ResponseEntity<?> register(@RequestBody ParentAddDto registerDTO) {
        if (parentDao.existsByUsername(registerDTO.getEmail())) {
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        Parent parent = new Parent();
        parent.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        parent.setUsername(registerDTO.getEmail());

        parent.setFirstname(registerDTO.getFirstname());
        parent.setLastname(registerDTO.getLastname());
        parent.setBirthdate(registerDTO.getBirthdate());
        parent.setAdresse(registerDTO.getAdresse());
        parent.setCity(registerDTO.getCity());
        parent.setMobile(registerDTO.getMobile());
        parent.setEmail(registerDTO.getEmail());
        parent.setProfession(registerDTO.getProfession());

        Role role = roleDao.findByName("PARENT").orElseThrow(() -> new RuntimeException("Role not found"));
       /* if (!entityManager.contains(role)) {
            // If the role is detached, merge it within a transaction
            role = entityManager.merge(role);
        }*/
        parent.setRoles(Collections.singletonList(role));
        parentService.saveParent(parent);
        return new ResponseEntity<>("Username registered successfully", HttpStatus.OK);
    }





   /* @GetMapping(path = "/parents")
    public ResponseEntity<List<Parent>> getAllParents() {

        List<Parent> parents = parentService.getAllParents();

        return new ResponseEntity<>(parents, HttpStatus.OK); // renvoie vers le front
    }

    @GetMapping(path = "/parents/{parentId}")
    public ResponseEntity<Parent> getParent (@PathVariable("parentId") Long id) {

        Parent foundParent = parentService.getParentById(id);

        return new ResponseEntity<>(foundParent, HttpStatus.OK);

    }*/


}
