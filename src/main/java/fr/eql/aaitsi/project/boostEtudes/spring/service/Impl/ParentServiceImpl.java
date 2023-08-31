package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.exception.UserException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ParentDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentDao parentDao;


    @Override
    public Parent saveParent(Parent newParent) {
        Parent add = parentDao.save(newParent);
        return add;
    }

    @Override
    public List<Parent> getAllParents() {
        List<Parent> list = parentDao.findAll();

        if (list.size() > 0) {
            return list;
        } else {
            throw new UserException("No parent found");

        }
    }

    @Override
    public Parent getParentById(Long parentId) {
        Optional<Parent> foundParent = parentDao.findById(parentId);

        if (foundParent.isPresent()) {
            return foundParent.get();
        } else throw new UserException("Parent not found by this id");
        // pas besoin de faire l'opération dans le controller car ça renverra une exception.
    }


}
