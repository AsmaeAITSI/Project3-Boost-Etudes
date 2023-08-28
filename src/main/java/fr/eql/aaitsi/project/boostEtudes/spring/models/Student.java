package fr.eql.aaitsi.project.boostEtudes.spring.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Student extends User{
    @ManyToOne
    @JoinColumn(name = "school_level_id")
    private SchoolLevel schoolLevel;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;



}
