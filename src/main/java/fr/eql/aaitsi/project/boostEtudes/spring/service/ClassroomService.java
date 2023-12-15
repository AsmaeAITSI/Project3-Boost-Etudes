package fr.eql.aaitsi.project.boostEtudes.spring.service;

import fr.eql.aaitsi.project.boostEtudes.spring.models.Classroom;

import java.util.Optional;


public interface ClassroomService {

    public Optional<Classroom> findRandomUnreservedClassroom();
}
