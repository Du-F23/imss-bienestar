package edu.mx.utvt.web.model.entities;

import edu.mx.utvt.web.model.repositories.DiagnosisRepository;
import edu.mx.utvt.web.model.repositories.DiseaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Rollback(value = false)
public class DiseaseTest {
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Test
    @DisplayName("Crear Disease")
    void createDisease(){
        Disease disease = null;
        disease = diseaseRepository.save(Disease.builder().commonName("Infeccion Estomacal").medicalName("Treda").description("El paciente sufre de una infeccion estomacal").build());
        log.info("Disease: " + disease.toString());
        Assertions.assertNotNull(disease);
    }

    @Test
    @DisplayName("Actualizar Disease")
    void updateDisease(){
        Long diseaseId = 3L;
        Optional<Disease> optionalDisease = diseaseRepository.findById(diseaseId);
        Disease disease = optionalDisease.get();
        disease.setDescription("Hola");
        diseaseRepository.save(disease);
        log.info("Disease Actualizado");
    }
}
