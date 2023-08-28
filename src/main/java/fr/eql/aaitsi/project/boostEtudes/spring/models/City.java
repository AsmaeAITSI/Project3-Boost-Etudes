package fr.eql.aaitsi.project.boostEtudes.spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue()
    private String cityId;
    private String name;
    private Integer postalCode;

}
