package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Course;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.CourseRequestDto;

public interface CourseService {

    public Course createCourse(Long availabilityId, Long parentId);

}
