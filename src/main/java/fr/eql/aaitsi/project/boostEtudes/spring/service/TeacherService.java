package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeacherService {

    public Teacher saveTeacher(Teacher newTeacher);

    public List<Teacher> getAllTeacher();

    Teacher getTeacherById(Long teacherId);
}
