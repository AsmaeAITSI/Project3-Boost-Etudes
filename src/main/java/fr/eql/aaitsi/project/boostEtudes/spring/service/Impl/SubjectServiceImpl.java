package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Subject;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.SubjectDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectDao subjectDao;

    public List<Subject> getAllSubjects() {
        return subjectDao.findAll();

    }


}
