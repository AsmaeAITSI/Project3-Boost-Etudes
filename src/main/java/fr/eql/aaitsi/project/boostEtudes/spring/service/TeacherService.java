package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityDto;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.TeacherDto;
import java.util.List;


public interface TeacherService {

    public Teacher saveTeacher(Teacher newTeacher);

    public List<Teacher> getAllTeacher();

    public Teacher updateTeacher(Long id, Teacher updatedTeacher);

    public TeacherDto mapTeacherToDTO(Teacher teacher);

    public Teacher getTeacherWithSubjectsById(Long teacherId);

    public void addAvailability(Long teacherId, Availability availability);

    public List<AvailabilityDto> getAvailabilitiesByTeacherId(Long teacherId);

    public List<Teacher> getAllTeachers();
}
