package fr.eql.aaitsi.project.boostEtudes.spring.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends UserEntity {

    private String profession;



    @ElementCollection(targetClass = Subject.class)
    @CollectionTable(name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"))
    @Enumerated(EnumType.STRING)
    private List<Subject> subjects = new ArrayList<>();


}
