package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;

import java.util.List;

public interface ParentService {

    Parent saveParent (Parent newParent);
    List<Parent> getAllParents();

    Parent getParentById(Long parentId);


}
