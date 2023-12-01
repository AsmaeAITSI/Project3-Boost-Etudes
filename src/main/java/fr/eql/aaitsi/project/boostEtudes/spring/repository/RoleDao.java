package fr.eql.aaitsi.project.boostEtudes.spring.repository;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
