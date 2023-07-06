package edu.mx.utvt.web.model.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.mx.utvt.web.model.enums.MedicalSpecialty;
import edu.mx.utvt.web.model.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class SpecialtyTest {
	
	@Autowired
	private SpecialtyRepository specialtyRepository;

	@Test
	@DisplayName("Creación de dos especialidades medicas")
	void test() {	
		
		if (this.specialtyRepository.findByMedicalSpecialty(MedicalSpecialty.MEDICAL_GENETICS).isEmpty()) {
			log.info("Especialidad creada");
			this.specialtyRepository.save(Specialty.builder().medicalSpecialty(MedicalSpecialty.MEDICAL_GENETICS).build());
		} else {
			log.info("La especialidad ya existe");
		}			
	}
	

}
