package fr.eql.aaitsi.project.boostEtudes.spring.repository;


import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityDao extends JpaRepository<Availability, Long> {

    Optional<Availability> findById(Long id);


    @Query("SELECT a FROM Availability a WHERE a.teacher.id = :teacherId AND a.day = :day AND a.startTime = :startTime AND a.availabilityId <> :availabilityId AND a.isReserved = false")
    List<Availability> findOtherUnreservedAvailabilities(
            @Param("teacherId") Long teacherId,
            @Param("day") LocalDate day,
            @Param("startTime") LocalTime startTime,
            @Param("availabilityId") Long availabilityId
    );

    @Query("SELECT NEW fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityProjection(" +
            "a.availabilityId, a.day, a.startTime, a.endTime, t.firstname, t.lastname, s.name, a.isReserved) " +
            "FROM Availability a " +
            "JOIN a.subject s " +
            "JOIN a.teacher t " +
            "WHERE a.isReserved = false " +
            "ORDER BY t.lastname ASC, t.firstname ASC")
    List<AvailabilityProjection> findAllProjectedByOrderByTeacherLastnameAscTeacherFirstnameAsc();
}

