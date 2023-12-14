package fr.eql.aaitsi.project.boostEtudes.spring.models.dto;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAddDto {

    private String firstname;
    private String lastname;
    private LocalDate birthdate;
    private String adresse;
    private String city;
    private String mobile;
    private String email;
    private String password;
    private Parent parent;


}
