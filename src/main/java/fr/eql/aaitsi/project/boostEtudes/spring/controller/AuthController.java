package fr.eql.aaitsi.project.boostEtudes.spring.controller;


import fr.eql.aaitsi.project.boostEtudes.spring.jwt.JWTGenerator;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AuthRequest;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AuthResponse;

import fr.eql.aaitsi.project.boostEtudes.spring.service.SecurityService;
import fr.eql.aaitsi.project.boostEtudes.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin("${front.url}")
public class AuthController {

    private SecurityService securityService;
    private AuthenticationManager authenticationManager;
    private JWTGenerator jwtGenerator;
    private UserService userService;

    public AuthController(SecurityService securityService, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, UserService userService) {
        this.securityService = securityService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication); //stocke notre objet authentification dans le contexte
        String token = jwtGenerator.generateToken(authentication);
        UserEntity userEntity = securityService.getUserByUsername(authRequest.getUsername());
        return ResponseEntity.ok(
                new AuthResponse(
                        token, userEntity.getUserId(), userEntity.getUsername(), userEntity.getFirstname(),
                        userEntity.getLastname(), userEntity.getRoles(), userEntity.getBirthdate(), userEntity.getAdresse(), userEntity.getCity(), userEntity.getMobile(), userEntity.getEmail()
                ));
        }


    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<UserEntity> getUser (@PathVariable("userId") Long id) {

        UserEntity foundUser = userService.getUserById(id);

        return new ResponseEntity<>(foundUser, HttpStatus.OK);

    }









}




