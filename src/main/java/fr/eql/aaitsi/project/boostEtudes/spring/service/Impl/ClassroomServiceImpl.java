package fr.eql.aaitsi.project.boostEtudes.spring.service.Impl;


import fr.eql.aaitsi.project.boostEtudes.spring.models.Classroom;
import fr.eql.aaitsi.project.boostEtudes.spring.repository.ClassroomDao;
import fr.eql.aaitsi.project.boostEtudes.spring.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomDao classroomDao;

    @Override
    public Optional<Classroom> findRandomUnreservedClassroom() {
        // Récupérer toutes les salles de classe non réservées
        List<Classroom> unreservedClassrooms = classroomDao.findByIsReservedFalse();

        if (!unreservedClassrooms.isEmpty()) {
            // Générer un index aléatoire pour choisir une salle de classe
            int randomIndex = (int) (Math.random() * unreservedClassrooms.size());
            return Optional.of(unreservedClassrooms.get(randomIndex));
        } else {
            // Aucune salle de classe non réservée n'est disponible
            return Optional.empty();
        }
    }

}
