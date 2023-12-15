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

    private Integer roomNumber;
    private String parentFirstname;
    private String parentLastname;
    private String teacherFirstname;
    private String teacherLastname;
    private String subject;
    private LocalDate courseDate;
    private LocalTime courseStart;
    private LocalTime courseEnd;
    private Double price;
}
