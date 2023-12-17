package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Course;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.CourseRequestDto;
import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {

    public Course createCourse(Long availabilityId, Long parentId);

    public List<CourseResponseDto> getCoursesByParent(Long parentId) ;

}
