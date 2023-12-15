package fr.eql.aaitsi.project.boostEtudes.spring.models.dto;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;


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
    String subjects;
    Boolean isReserved;







}
