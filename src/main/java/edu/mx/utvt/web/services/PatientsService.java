package edu.mx.utvt.web.services;

import edu.mx.utvt.web.model.entities.Patient;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface PatientsService {

    List<Patient> showAll();

    @Transactional
    Optional<Patient> showOne(Long id);
}
