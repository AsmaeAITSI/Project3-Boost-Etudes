package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;

import java.util.List;

public interface ParentService {

    Parent saveParent (Parent newParent);
    List<Parent> getAllParents();

    Parent getParentById(Long parentId);

    public Parent updateParent(Long id, Parent updatedParent);


}
