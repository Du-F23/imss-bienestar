package edu.mx.utvt.web.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mx.utvt.web.model.entities.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
