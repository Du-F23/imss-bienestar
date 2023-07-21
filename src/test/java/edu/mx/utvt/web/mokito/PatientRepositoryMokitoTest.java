package edu.mx.utvt.web.mokito;

import edu.mx.utvt.web.model.data.PatientData;
import edu.mx.utvt.web.model.entities.Patient;
import edu.mx.utvt.web.model.enums.UserStatus;
import edu.mx.utvt.web.model.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Slf4j
@Rollback(value = false)
@AutoConfigureMockMvc
public class PatientRepositoryMokitoTest {

    @Mock
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Prueba para guardar un paciente usando mockito")
    void saveMockPatientTest() {
        log.info("Guarda el mock patient");
        Patient patient = null;
        patient = Patient.builder().firstName("Fernanda").lastName("Flores").birthDate(new Date()).bloodType("AO+").email("ferflo@gmail.com").userStatus(UserStatus.OPEN).rfc("FLAFD0215243").createdDate(new Date()).lastModifiedDate(new Date()).build();

        when(this.patientRepository.save(patient)).thenReturn(PatientData.newPatient(patient));
        patient = this.patientRepository.save(patient);

        Assertions.assertNotNull(patient);
    }

    @Test
    @DisplayName("Prueba para buscar paciente con el id, usando mockito")
    void findPatientById(){
        Optional<Patient> patientOptional = null;

        when(this.patientRepository.findById(1L)).thenReturn(PatientData.showOne(1L));

        patientOptional = this.patientRepository.findById(1L);

        Assertions.assertEquals(patientOptional.get().getId(), 1L);

        verify(this.patientRepository).findById(1L);
    }

    @Test
    @DisplayName("Prueba para buscar todos los pacientes usando mockito")
    void showAllPatients() {
        log.info("Obteniendo pacientes");
        List<Patient> patients = null;

        when(this.patientRepository.findAll()).thenReturn(PatientData.showAll());

        patients = this.patientRepository.findAll();

        verify(this.patientRepository).findAll();

        Assertions.assertNotNull(patients);
    }
}
