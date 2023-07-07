package edu.mx.utvt.web.model.entities;

import edu.mx.utvt.web.model.repositories.DiagnosisRepository;
import edu.mx.utvt.web.model.repositories.DiseaseRepository;
import edu.mx.utvt.web.model.repositories.MedicineRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Rollback(value = false)

public class DiagnosisTest {
    @Autowired
    private DiagnosisRepository diagnosisRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    @BeforeEach
    void  testBeforeEach(){
        log.info("Inicia el test");
        log.info("Diagnosticos registrados: " + this.diagnosisRepository.count());
    }


    @Test
    @DisplayName("Crear diagnostico")
    void createDiagnosisTest(){
        Diagnosis diagnosis = null;
        diagnosis = diagnosisRepository.save(Diagnosis.builder().comments("Tomar una cada 8 hrs").build());
        log.info(diagnosis.toString());
        diagnosisRepository.save(diagnosis);
        Assertions.assertNotNull(diagnosis);
        log.info("Diagnostico Creado: " + diagnosis.toString());
    }

    @Test
    @DisplayName("Actualizar Diagnostico")
    void updateDiagnosis(){
        Long dignosisId= 3L;
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(dignosisId);
        Diagnosis diagnosis = optionalDiagnosis.get();
        log.info("Valor anterior: " + diagnosis.toString());
        diagnosis.setComments("Tomar una pastilla cada 4 Horas");
        log.info("Valor actual: " + diagnosis.toString());
        diagnosisRepository.save(diagnosis);
    }

    @Test
    @DisplayName("Borrar Diagnostico")
    void deleteDiagnosis(){
        Long diagnosisId = 2L;
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(diagnosisId);
        Diagnosis diagnosis = optionalDiagnosis.get();
        log.info("Eliminaras el diagnostico: " + diagnosis.getId());
        diagnosisRepository.delete(diagnosis);
        Assertions.assertFalse(diagnosisRepository.existsById(diagnosisId));
        log.info("Diagnostico borrado satisfactoriamente");
    }
}
