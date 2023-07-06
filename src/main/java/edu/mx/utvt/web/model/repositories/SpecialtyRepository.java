package edu.mx.utvt.web.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mx.utvt.web.model.entities.Specialty;
import edu.mx.utvt.web.model.enums.MedicalSpecialty;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
	
	
	Optional<Specialty> findByMedicalSpecialty(MedicalSpecialty medicalSpecialty);

}
