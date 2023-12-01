package fr.eql.aaitsi.project.boostEtudes.spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;
    public String name;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<Teacher> teachers;

    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<Course> courses;

    public Subject(String name) {
        this.name = name;
    }
}

