package fr.eql.aaitsi.project.boostEtudes.spring.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailabilityDto {

    private Long availabilityId;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;


}
