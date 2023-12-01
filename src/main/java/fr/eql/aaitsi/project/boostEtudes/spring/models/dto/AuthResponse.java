package fr.eql.aaitsi.project.boostEtudes.spring.models.dto;


import fr.eql.aaitsi.project.boostEtudes.spring.models.Role;
import lombok.Data;


import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;


@Data
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private List<Role> roles;
    private LocalDate birthdate;
    private String adresse;
    private String city;
    private String mobile;
    private String email;

    public AuthResponse(String accessToken, Long id, String username, String firstname, String lastname, List<Role> roles, LocalDate birthdate, String adresse, String city, String mobile, String email) {
        this.accessToken = accessToken;
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
        this.birthdate = birthdate;
        this.adresse = adresse;
        this.city = city;
        this.mobile = mobile;
        this.email = email;
    }
}
