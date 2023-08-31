package fr.eql.aaitsi.project.boostEtudes.spring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(name="course_type")
public abstract class Course {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Classroom classroom;

    @ElementCollection(targetClass = Subject.class)
    @CollectionTable(name = "course_subject",
            joinColumns = @JoinColumn(name = "course_id"))
    @Enumerated(EnumType.STRING)
    private List<Subject> subjects;

    @ElementCollection(targetClass = SchoolLevel.class)
    @CollectionTable(name = "course_school_levels",
            joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "school_level")
    @Enumerated(EnumType.STRING)
    private List<SchoolLevel> schoolLevels;

    private LocalDate courseDate;
    private LocalTime courseStart;
    private LocalTime courseEnd;
    private Double price;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();


}
