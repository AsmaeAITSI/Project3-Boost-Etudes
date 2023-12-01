package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;

public interface UserService {

    UserEntity getUserById(Long userId);
}
