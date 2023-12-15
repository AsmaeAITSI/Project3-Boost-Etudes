package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityDto;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.AvailabilityProjection;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.AvailabilityDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.AvailabilityService;
import fr.eql.aaitsi.project.boostEtudes.spring.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private TeacherService teacherService; // Vous devez avoir un service TeacherService


    @Autowired
    private AvailabilityDao availabilityDao;

    @Override
    public List<AvailabilityDto> getAllAvailabilities() {
        List<AvailabilityDto> allAvailabilities = new ArrayList<>();

        // Récupérez tous les enseignants
        List<Teacher> teachers = teacherService.getAllTeachers();

        // Pour chaque enseignant, récupérez ses disponibilités
        for (Teacher teacher : teachers) {
            List<Availability> teacherAvailabilities = teacher.getAvailabilities();

            // Convertissez les disponibilités en DTO et ajoutez-les à la liste
            List<AvailabilityDto> teacherAvailabilityDtos = teacherAvailabilities.stream()
                    .map(availability -> new AvailabilityDto(
                            availability.getAvailabilityId(),
                            availability.getDay(),
                            availability.getStartTime(),
                            availability.getEndTime()
                    ))
                    .collect(Collectors.toList());

            allAvailabilities.addAll(teacherAvailabilityDtos);
        }

        return allAvailabilities;
    }

    @Transactional
    @Override
    public List<AvailabilityProjection> getAllAvailabilitiesWithTeacherInfo() {
        return availabilityDao.findAllProjectedByOrderByTeacherLastnameAscTeacherFirstnameAsc();
    }


    @Transactional
    @Override
    public void deleteOtherUnreservedAvailabilities(Long teacherId, LocalDate day, LocalTime startTime, Long availabilityId) {
        List<Availability> otherUnreservedAvailabilities = availabilityDao.findOtherUnreservedAvailabilities(teacherId, day, startTime, availabilityId);

        availabilityDao.deleteAll(otherUnreservedAvailabilities);
    }

}
