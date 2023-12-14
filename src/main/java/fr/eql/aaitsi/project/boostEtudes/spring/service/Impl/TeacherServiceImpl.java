package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.exception.UserException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Subject;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityDto;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.TeacherDto;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.SubjectDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.TeacherDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Teacher saveTeacher(Teacher newTeacher) {
        Teacher add = teacherDao.save(newTeacher);
        return add;
    }

    @Override
    public List<Teacher> getAllTeacher() {
        List<Teacher> list = teacherDao.findAll();

        if (list.size() > 0) {
            return list;
        } else {
            throw new UserException("No teacher found");

        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        List<Teacher> list = teacherDao.findAll();
        if (list.size() > 0) {
            return list;
        } else {
            throw new UserException("No teacher found");
        }
    }

    @Transactional
    @Override
    public Teacher getTeacherWithSubjectsById(Long teacherId) {
        Optional<Teacher> optionalTeacher = teacherDao.findByIdWithSubjects(teacherId);
        return optionalTeacher.orElse(null);
    }


    @Override
    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher existingTeacher = teacherDao.findById(id).orElseThrow(
                () -> new UserException("User not found for this id : " + id));
        existingTeacher.setFirstname(updatedTeacher.getFirstname());
        existingTeacher.setLastname(updatedTeacher.getLastname());
        existingTeacher.setEmail(updatedTeacher.getEmail());
        existingTeacher.setUsername(updatedTeacher.getEmail());
        existingTeacher.setProfession(updatedTeacher.getProfession());
        String newPassword = updatedTeacher.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            existingTeacher.setPassword(encodedPassword);
        }

        return teacherDao.save(existingTeacher);
    }


    @Override
    @Transactional
    public TeacherDto mapTeacherToDTO(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setFirstname(teacher.getFirstname());
        dto.setLastname(teacher.getLastname());
        dto.setBirthdate(teacher.getBirthdate());
        dto.setAdresse(teacher.getAdresse());
        dto.setCity(teacher.getCity());
        dto.setProfession(teacher.getProfession());
        dto.setMobile(teacher.getMobile());
        dto.setEmail(teacher.getEmail());

        List<String> subjectNames = teacher.getSubjects().stream()
                .map(Subject::getName)
                .collect(Collectors.toList());
        dto.setSubjectNames(subjectNames);

        return dto;
    }
    @Override
    @Transactional
    public void addAvailability(Long teacherId, Availability availability) {

        Teacher teacher = teacherDao.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Enseignant non trouvé"));
        availability.setTeacher(teacher);
        List<Availability> availabilities = teacher.getAvailabilities();
        availabilities.add(availability);

        teacherDao.save(teacher);

    }

    @Override
    public List<AvailabilityDto> getAvailabilitiesByTeacherId(Long teacherId) {
        List<Availability> availabilities = teacherDao.getAvailabilitiesByTeacherId(teacherId);

        List<AvailabilityDto> availabilityDTOs = availabilities.stream()
                .map(availability -> new AvailabilityDto(
                        availability.getAvailabilityId(),
                        availability.getDay(),
                        availability.getStartTime(),
                        availability.getEndTime()
                ))
                .collect(Collectors.toList());

        return availabilityDTOs;
    }
}
