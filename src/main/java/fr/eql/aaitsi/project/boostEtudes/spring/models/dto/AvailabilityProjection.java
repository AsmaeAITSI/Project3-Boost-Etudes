package fr.eql.aaitsi.project.boostEtudes.spring.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityProjection {

    Long AvailabilityId;
    LocalDate Day;
    LocalTime StartTime;
    LocalTime EndTime;
    String TeacherFirstname;
    String TeacherLastname;


}
