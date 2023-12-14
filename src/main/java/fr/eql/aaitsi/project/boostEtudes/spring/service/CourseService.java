package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.dto.CourseRequestDto;

public interface CourseService {

    public void createCourse(CourseRequestDto courseRequest);
}
