package edu.mx.utvt.web.model.entities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import edu.mx.utvt.web.model.enums.MedicalSpecialty;
import edu.mx.utvt.web.model.repositories.DoctorRepository;
import edu.mx.utvt.web.model.repositories.SpecialtyRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
class DoctorTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @BeforeAll
    void testBeforeAll() {
        log.info("Inicia la validación y la limpieza de datos");
        this.doctorRepository.deleteAll();
        log.info("Doctores actuales: " + doctorRepository.count());
    }

    @BeforeEach
    void testBeforeEach() {
        log.info("Antes de cada caso de Prueba");
        log.info("Doctores actuales: " + doctorRepository.count());
    }


    @Test
    @DisplayName("Escenario: Alta exitosa de Medico sin especialidad, "
            + "Given: Medico con datos correctos y sin especialidad "
            + "WHEN es dado de alta "
            + "THEN se genera la alta en la BD ")
    void testAltaMedico() {
        log.info("Alta de Medico");

        Doctor doctor = null;
        Specialty specialty = null;
        specialty = specialtyRepository.findById(1L).get();

        doctor = this.doctorRepository.save(
                Doctor.builder().firstName("Fernando").lastName("Duarte").rfc("DUVF021021HM").cedula("20000000").speciality(specialty).build());

        log.info("Detalle de medico: ", doctor.toString());
    }


    @Nested
    class AltaMedicoFallida {
        @Test
        @DisplayName("Escenario: Alta fallida de Medico con RFC duplicado, "
                + "Given: Medico con RFC duplicado "
                + "WHEN es dado de alta "
                + "THEN se genera una excepción DataIntegrityViolationException ")
        void test() {

            log.info("Alta de medico fallida");

            Doctor doctor = null;
            doctor = Doctor.builder().firstName("Pato").lastName("Perez").rfc("AAAA100000").cedula("0000000000").build();

            try {

                doctorRepository.save(doctor);

            } catch (DataIntegrityViolationException dataIntegrityViolationException) {

                log.info("Excepción capturada: " + dataIntegrityViolationException.getMessage());
            }

        }
    }


    @Nested
    class AltaDoctorConEspecialidad {

        @DisplayName("Escenario: Alta exitosa de Medico con especialidad de tipo ALLERGY_AND_IMMUNOLOGY, "
                + "Given: Medico con especialidad de ALLERGY_AND_IMMUNOLOGY "
                + "WHEN es dado de alta "
                + "THEN se genera el alta exitosa ")
        @Test
        void test() {
            log.info("Alta exitosa de Medico con especialidad de tipo ALLERGY_AND_IMMUNOLOGY");

            Specialty specialty = null;
            Doctor doctor = null;

            specialty = specialtyRepository.findByMedicalSpecialty(MedicalSpecialty.ALLERGY_AND_IMMUNOLOGY)
                    .get();
            doctor = Doctor.builder().firstName("Super Pato").lastName("Perez").rfc("AAAA200000")
                    .cedula("2000000000").speciality(specialty).build();

            doctorRepository.save(doctor);
        }
    }


    @Nested
    class ActualizacionDoctorConEspecialidad {

        @DisplayName("Escenario: Actualización exitosa de Medico y asignación de la especialidad de tipo ANESTHESIOLOGY, "
                + "Given: Medico sin especialidad "
                + "WHEN es actualizado se le asignala especialidad de tipo ANESTHESIOLOGY"
                + "THEN se genera la actualización exitosa ")
        @Test
        void test() {

            Specialty specialty = null;
            Doctor doctor = null;


            specialty = specialtyRepository.findByMedicalSpecialty(MedicalSpecialty.ANESTHESIOLOGY)
                    .get();
            doctor = doctorRepository.findByRfc("AAAA100000").get();

            doctor.setSpeciality(specialty);
            doctorRepository.save(doctor);
        }
    }


    @AfterAll
    void testAfterAll() {
        log.info("Finaliza la ejecución de pruebas");
        log.info("Doctores actuales: " + doctorRepository.count());
    }

}
