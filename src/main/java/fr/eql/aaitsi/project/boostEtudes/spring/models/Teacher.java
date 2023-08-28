package fr.eql.aaitsi.project.boostEtudes.spring.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends User{

    private String profession;
}
