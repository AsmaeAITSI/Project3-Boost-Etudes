package fr.eql.aaitsi.project.boostEtudes.spring.repository;


import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityDao extends JpaRepository<Availability, Long> {
    @Query("SELECT NEW fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityProjection(a.availabilityId, a.day, a.startTime, a.endTime, t.firstname, t.lastname) FROM Availability a JOIN a.teacher t ORDER BY t.lastname ASC, t.firstname ASC")
    List<AvailabilityProjection> findAllProjectedByOrderByTeacherLastnameAscTeacherFirstnameAsc();


}
