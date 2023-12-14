package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Course;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.CourseRequestDto;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.CourseDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    public void createCourse(CourseRequestDto courseRequest) {
        // Effectuez les validations nécessaires avant la création du cours

        Course course = new Course();
        course.setClassroom(courseRequest.getClassroom());
        course.setSubjects(courseRequest.getSubjects());
        course.setCourseDate(courseRequest.getCourseDate());
        course.setCourseStart(courseRequest.getCourseStart());
        course.setCourseEnd(courseRequest.getCourseEnd());

        // Définissez le prix par défaut à 50 euros si le prix n'est pas spécifié
        course.setPrice(courseRequest.getPrice() != null ? courseRequest.getPrice() : 50.0);
        // Ajoutez d'autres champs du cours...

        courseDao.save(course);
    }


}
