package edu.mx.utvt.web.model.entities;

import edu.mx.utvt.web.model.enums.UserStatus;
import edu.mx.utvt.web.model.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void  testBeforeEach(){
        log.info("Inicia el test");
        log.info("Patients registrados: " + this.patientRepository.count());
    }

    @Test
    @DisplayName("Creación de Patients")
    void testCreatePatient(){
        log.info("Inciando test para crear pasientes");
        Patient patient = null;

        patient = this.patientRepository.save(Patient.builder().firstName("Fernando").lastName("Duarte").birthDate(new Date()).bloodType("AO+").email("ferduarte@gmail.com").userStatus(UserStatus.OPEN).rfc("DUVF021021HM").createdDate(new Date()).lastModifiedDate(new Date()).build());

        Assertions.assertNotNull(patient);

        log.info("Paciente creado con los siguientes datos: " + patient.toString());
    }

    @Test
    @DisplayName("Leer todos los pacientes")
    void testFindAllPatients(){
        log.info("Buscando a todos los pacientes");
        List<Patient> patientsList = patientRepository.findAll();

        Assertions.assertNotNull(patientsList);

        for (Patient patient : patientsList) {
            log.info("ID: " + patient.getId());
            log.info("Nombre: " + patient.getFirstName() + " " + patient.getLastName());
            // Imprimir más información si es necesario
        }
    }

    @Test
    @DisplayName("Actualizar Pacientes")
    @WithMockUser("admin")
    void testUpdateUser(){
        log.info("Actualizar Pacientes");
        Long patientId = 1L;

        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        Assertions.assertTrue(optionalPatient.isPresent());

        Patient patient = optionalPatient.get();

        patient.setUserStatus(UserStatus.APPROVED);
        patient.setLastModifiedDate(new Date());

        log.info("Paciente actualizado: " + patient);
    }

    @Test
    @DisplayName("Eliminar Pacientes")
    void testDeletePatients(){
        log.info("Eliminar Pacientes");
        Long patientId=1L;

        Optional<Patient> optionalPatient = patientRepository.findById(patientId);

        Patient patient = optionalPatient.get();
        patientRepository.delete(patient);
        Assertions.assertFalse(patientRepository.existsById(patientId));
        log.info("Paciente Eliminado Correctamente");
    }
}
