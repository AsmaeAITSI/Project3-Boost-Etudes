package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.exception.UserException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.UserDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserEntity getUserById(Long userId) {
        Optional<UserEntity> foundUser = userDao.findById(userId);

        if (foundUser.isPresent()) {
            return foundUser.get();
        } else throw new UserException("User not found by this id");
    }
}
