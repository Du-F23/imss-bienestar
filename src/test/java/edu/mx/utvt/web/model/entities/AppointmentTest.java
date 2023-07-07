package edu.mx.utvt.web.model.entities;

import edu.mx.utvt.web.model.enums.AppointmentStatus;
import edu.mx.utvt.web.model.enums.UserStatus;
import edu.mx.utvt.web.model.repositories.AppointmentRepository;
import edu.mx.utvt.web.model.repositories.DiagnosisRepository;
import edu.mx.utvt.web.model.repositories.DoctorRepository;
import edu.mx.utvt.web.model.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Rollback(value = false)

public class AppointmentTest {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Test
    @DisplayName("Crear Appointment")
    void createAppointment() {
        Appointment appointment = null;
        appointment = Appointment.builder().end(new Date(6)).appointmentStatus(AppointmentStatus.NO_ATTENDANCE).reason("Unabiled").build();
        log.info(appointment.toString());
        appointmentRepository.save(appointment);
        Assertions.assertNotNull(appointment);
        log.info("Appointment Creado: " + appointment.toString());
    }

    @Test
    @DisplayName("Actualizar Appoitment")
    void updateAppointment(){
        Long appointmentId=5L;
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentId);
        Assertions.assertTrue(optionalAppointment.isPresent());
        Appointment appointment = optionalAppointment.get();
        Optional<Doctor> optionalDoctor = doctorRepository.findById(2L);
        Doctor doctor = optionalDoctor.get();
        Optional<Patient> optionalPatient = patientRepository.findById(1L);
        Patient patient = optionalPatient.get();
        Optional<Diagnosis> optionalDiagnosis = diagnosisRepository.findById(3L);
        Diagnosis diagnosis = optionalDiagnosis.get();
        appointment.setAppointmentStatus(AppointmentStatus.CANCEL);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDiagnosis(diagnosis);
        appointmentRepository.save(appointment);
//        log.info("Appoiment Actualizado: " + appointment.toString() );
    }

    @Test
    @DisplayName("Listar Appoitments")
    void showAppointments(){
        log.info("Buscando todas las citas");
        List<Appointment> appointmentList = appointmentRepository.findAll();
        Assertions.assertNotNull(appointmentList);

        for (Appointment appointment : appointmentList) {
            log.info("ID: " + appointment.getId());
            log.info("Nombre: " + appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName());
            log.info("Razon: " + appointment.getReason());
            // Imprimir más información si es necesario
        }

    }
}
