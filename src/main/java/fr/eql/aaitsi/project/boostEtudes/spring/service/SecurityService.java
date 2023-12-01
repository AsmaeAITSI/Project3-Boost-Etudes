package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    public UserEntity getUserByUsername(String username);



}
