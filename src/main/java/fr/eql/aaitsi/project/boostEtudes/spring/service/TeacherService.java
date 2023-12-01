package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityDto;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.TeacherDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface TeacherService {

    public Teacher saveTeacher(Teacher newTeacher);

    public List<Teacher> getAllTeacher();

    Teacher getTeacherById(Long teacherId);

    public Teacher updateTeacher(Long id, Teacher updatedTeacher);

    public TeacherDto mapTeacherToDTO(Teacher teacher);

    public Teacher getTeacherWithSubjectsById(Long teacherId);

    public void addAvailability(Long teacherId, Availability availability);

    public List<Availability> getAvailabilitiesByTeacher(Long teacherId);

    public List<AvailabilityDto> getAvailabilitiesByTeacherId(Long teacherId);

    public List<Teacher> getAllTeachers();




}
