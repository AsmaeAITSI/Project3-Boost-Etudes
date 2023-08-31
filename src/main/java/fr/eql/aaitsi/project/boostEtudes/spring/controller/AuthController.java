package fr.eql.aaitsi.project.boostEtudes.spring.controller;


import fr.eql.aaitsi.project.boostEtudes.spring.jwt.JWTGenerator;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Role;
import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AuthRequest;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AuthResponse;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.ParentAddDto;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ParentDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.RoleDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.UserDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    ParentService parentService;
    private AuthenticationManager authenticationManager;
    ParentDao parentDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    public AuthController(ParentService parentService, AuthenticationManager authenticationManager, ParentDao parentDao, RoleDao roleDao, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.parentService = parentService;
        this.authenticationManager = authenticationManager;
        this.parentDao = parentDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication); //stocke notre objet authentification dans le contexte
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);

    }
}




