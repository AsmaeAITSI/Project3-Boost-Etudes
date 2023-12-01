package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.exception.UserException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Education;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.EducationDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationDao educationDao;


    @Override
    public Education saveEducationMember(Education newEducationMember) {
        Education add = educationDao.save(newEducationMember);
        return add;
    }

    @Override
    public List<Education> getAllEducationMembers() {
        List<Education> list = educationDao.findAll();

        if (list.size() > 0) {
            return list;
        } else {
            throw new UserException("No Education member found");

        }
    }

    @Override
    public Education getEducationMemberById(Long educationMemberId) {
        Optional<Education> foundEducationMember = educationDao.findById(educationMemberId);

        if (foundEducationMember.isPresent()) {
            return foundEducationMember.get();
        } else throw new UserException("Education member not found by this id");
        // pas besoin de faire l'opération dans le controller car ça renverra une exception.
    }

}
