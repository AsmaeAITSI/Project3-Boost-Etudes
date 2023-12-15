package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityDto;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityProjection;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AvailabilityService {

    public List<AvailabilityDto> getAllAvailabilities();

    public void deleteOtherUnreservedAvailabilities(Long teacherId, LocalDate day, LocalTime startTime, Long availabilityId);


    public List<AvailabilityProjection> getAllAvailabilitiesWithTeacherInfo();
}
