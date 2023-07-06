package edu.mx.utvt.web.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mx.utvt.web.model.entities.Doctor;



public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	Optional<Doctor> findByCedula(String cedula);
	Optional<Doctor> findByRfc(String rfc);

}
