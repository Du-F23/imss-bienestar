package edu.mx.utvt.web.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mx.utvt.web.model.entities.Diagnosis;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

}
