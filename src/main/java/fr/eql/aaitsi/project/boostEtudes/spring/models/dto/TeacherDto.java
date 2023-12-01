package fr.eql.aaitsi.project.boostEtudes.spring.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String adresse;
    private String city;
    private String profession;
    private String mobile;
    private String email;
    private String password;
    private List<String> subjectNames;
}
