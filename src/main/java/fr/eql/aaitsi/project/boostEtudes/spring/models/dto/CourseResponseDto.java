package fr.eql.aaitsi.project.boostEtudes.spring.models.dto;


import fr.eql.aaitsi.project.boostEtudes.spring.models.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {
    private Long courseId;
    private String teacherFirstName;
    private String teacherLastName;
    private Integer classroomNumber;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String subject;


    public static CourseResponseDto fromCourse(Course course) {
        CourseResponseDto dto = new CourseResponseDto();
        dto.setCourseId(course.getCourseId());
        dto.setTeacherFirstName(course.getAvailability().getTeacher().getFirstname());
        dto.setTeacherLastName(course.getAvailability().getTeacher().getLastname());
        dto.setSubject(course.getAvailability().getSubject().getName());
        dto.setClassroomNumber(course.getClassroom().getRoomNumber());
        dto.setDate(course.getAvailability().getDay());
        dto.setStartTime(course.getAvailability().getStartTime());
        dto.setEndTime(course.getAvailability().getEndTime());

        // Ajoutez d'autres propriétés
        return dto;
    }
}
