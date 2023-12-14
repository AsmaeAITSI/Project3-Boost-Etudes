package fr.eql.aaitsi.project.boostEtudes.spring.models.dto;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Classroom;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDto {

    private Classroom classroom;
    private List<Subject> subjects;
    private LocalDate courseDate;
    private LocalTime courseStart;
    private LocalTime courseEnd;
    private Double price;
}
