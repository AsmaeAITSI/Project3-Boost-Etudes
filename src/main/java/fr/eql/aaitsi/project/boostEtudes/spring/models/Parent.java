package fr.eql.aaitsi.project.boostEtudes.spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Parent extends User{

    private String profession;

    @OneToMany(mappedBy = "parent")
    private List<Student> children;

}
