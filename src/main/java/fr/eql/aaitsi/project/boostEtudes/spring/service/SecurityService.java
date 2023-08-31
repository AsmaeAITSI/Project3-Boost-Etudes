package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.exception.AccountExistsException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SecurityService extends UserDetailsService {

    Parent save(String username, String password) throws AccountExistsException;


}
