package fr.eql.aaitsi.project.boostEtudes.spring.repository;

import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);


}
