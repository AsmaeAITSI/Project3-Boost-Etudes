package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Role;
import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ParentDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.UserDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.SecurityService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Configuration
public class SecurityServiceImpl implements SecurityService {

    private UserDao userDao;


    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        System.out.println(user.getUsername());
        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userDao.findByUsername(username)
                .orElse(null); // Vous pouvez gérer le cas où l'utilisateur n'est pas trouvé.
    }

}
