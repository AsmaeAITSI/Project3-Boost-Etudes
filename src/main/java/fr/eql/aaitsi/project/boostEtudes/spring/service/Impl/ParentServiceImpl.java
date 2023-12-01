package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;

import fr.eql.aaitsi.project.boostEtudes.spring.exception.UserException;
import fr.eql.aaitsi.project.boostEtudes.spring.models.Parent;
import fr.eql.aaitsi.project.boostEtudes.spring.models.UserEntity;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ParentDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentDao parentDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Parent saveParent(Parent newParent) {
        Parent add = parentDao.save(newParent);
        return add;
    }

    @Override
    public Parent updateParent(Long id, Parent updatedParent) {
        Parent existingParent = parentDao.findById(id).orElseThrow(
                () -> new UserException("User not found for this id : " + id));

        // Mettez à jour les propriétés de l'objet existant avec les nouvelles valeurs
        existingParent.setFirstname(updatedParent.getFirstname());
        existingParent.setLastname(updatedParent.getLastname());
        existingParent.setEmail(updatedParent.getEmail());
        existingParent.setUsername(updatedParent.getEmail());
        existingParent.setProfession(updatedParent.getProfession());



        // Si un nouveau mot de passe est fourni, encodez-le et mettez à jour le mot de passe
        String newPassword = updatedParent.getPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            existingParent.setPassword(encodedPassword);
        }

        // Sauvegardez l'objet Parent mis à jour dans la base de données
        return parentDao.save(existingParent);
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
