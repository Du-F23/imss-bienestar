package edu.mx.utvt.web.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mx.utvt.web.model.entities.Disease;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {

}
