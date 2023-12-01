package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.exception.UserException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
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
import org.springframework.ui.ModelMap;

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
    @Transactional
    public Teacher getTeacherById(Long teacherId) {
        Optional<Teacher> foundTeacher = teacherDao.findById(teacherId);

        if (foundTeacher.isPresent()) {
            Teacher teacher = foundTeacher.get();
            teacher.getSubjects().size(); // Eager fetch des matières
            return teacher;
        } else throw new UserException("teacher not found by this id");
        // pas besoin de faire l'opération dans le controller car ça renverra une exception.

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

        // Mettez à jour les propriétés de l'objet existant avec les nouvelles valeurs
        existingTeacher.setFirstname(updatedTeacher.getFirstname());
        existingTeacher.setLastname(updatedTeacher.getLastname());
        existingTeacher.setEmail(updatedTeacher.getEmail());
        existingTeacher.setUsername(updatedTeacher.getEmail());
        existingTeacher.setProfession(updatedTeacher.getProfession());




        // Si un nouveau mot de passe est fourni, encodez-le et mettez à jour le mot de passe
        String newPassword = updatedTeacher.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            existingTeacher.setPassword(encodedPassword);
        }

        // Sauvegardez l'objet Parent mis à jour dans la base de données
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

        // Mapper les noms des matières
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

        // Set the reference to the teacher in the availability
        availability.setTeacher(teacher);

        // Add the availability to the teacher's list
        List<Availability> availabilities = teacher.getAvailabilities();
        availabilities.add(availability);

        teacherDao.save(teacher);

    }


    @Override
    public List<Availability> getAvailabilitiesByTeacher(Long teacherId) {
        Teacher teacher = teacherDao.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Enseignant non trouvé avec l'ID : " + teacherId));
        return teacher.getAvailabilities();
    }

    @Override
    public List<AvailabilityDto> getAvailabilitiesByTeacherId(Long teacherId) {
        List<Availability> availabilities = teacherDao.getAvailabilitiesByTeacherId(teacherId);

        // Convertir les entités en DTOs en utilisant le constructeur personnalisé
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
