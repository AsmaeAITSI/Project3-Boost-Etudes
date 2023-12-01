package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityDto;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityProjection;

import java.util.List;

public interface AvailabilityService {

    public List<AvailabilityDto> getAllAvailabilities();


    public List<AvailabilityProjection> getAllAvailabilitiesWithTeacherInfo();
}
