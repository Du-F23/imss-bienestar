package edu.mx.utvt.web.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mx.utvt.web.model.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
