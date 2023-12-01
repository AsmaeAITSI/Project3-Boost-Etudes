package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Education;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;

import java.util.List;

public interface EducationService {

    public Education saveEducationMember(Education newEducationMember);
    List<Education> getAllEducationMembers();

    Education getEducationMemberById(Long EducationMemberId);
}
