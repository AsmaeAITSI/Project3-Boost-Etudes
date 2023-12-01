package fr.eql.aaitsi.project.boostEtudes.spring.repository;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Subject;
import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectDao extends JpaRepository<Subject, Long> {

    Optional<Subject> findByName(String name);
}
