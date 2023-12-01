package fr.eql.aaitsi.project.boostEtudes.spring.repository;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long> {
    @Query("SELECT DISTINCT t FROM Teacher t " +
            "JOIN FETCH t.subjects " +
            "WHERE t.id = :teacherId")
    @Transactional
    Optional<Teacher> findByIdWithSubjects(@Param("teacherId") Long teacherId);


    @Query("SELECT a FROM Teacher t JOIN t.availabilities a WHERE t.id = :teacherId")
    List<Availability> getAvailabilitiesByTeacherId(@Param("teacherId") Long teacherId);
}
