package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Availability;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Classroom;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Course;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Subject;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Teacher;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.CourseRequestDto;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.AvailabilityDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ClassroomDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.CourseDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ParentDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.SubjectDao;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.TeacherDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.ClassroomService;
import fr.eql.aaitsi.project.boostEtudes.spring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseServiceImpl  implements CourseService{

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private ClassroomDao classroomDao;

    @Autowired
    private AvailabilityDao availabilityDao;

    @Autowired
    private ParentDao parentDao;


    @Transactional
    public Course createCourse(Long availabilityId, Long parentId) {
        // Chargez l'availability depuis la base de données
        Availability availability = availabilityDao.findById(availabilityId)
                .orElseThrow(() -> new EntityNotFoundException("Availability not found"));

        // Chargez le parent depuis la base de données
        Parent parent = parentDao.findById(parentId)
                .orElseThrow(() -> new EntityNotFoundException("Parent not found"));

        // Chargez une salle aléatoire non réservée depuis la base de données
        Classroom classroom = classroomService.findRandomUnreservedClassroom()
                .orElseThrow(() -> new EntityNotFoundException("No unreserved classroom available"));

        // Créez le cours avec le prix fixe
        Course course = new Course();
        course.setAvailability(availability);
        course.setClassroom(classroom);
        course.setPrice(50.0); // Prix fixe
        course.setParent(parent);

        // Marquez la salle comme réservée
        classroom.setIsReserved(true);
        classroomDao.save(classroom);

        availability.setReserved(true);
        availabilityDao.save(availability);

        // Sauvegardez le cours dans la base de données
        return courseDao.save(course);
    }



}





