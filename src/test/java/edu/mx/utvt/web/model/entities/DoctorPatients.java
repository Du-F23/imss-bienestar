package edu.mx.utvt.web.model.entities;

import edu.mx.utvt.web.model.repositories.DoctorRepository;
import edu.mx.utvt.web.model.repositories.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Rollback(value = false)

public class DoctorPatients {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Añadir ManyToMany de doctors a patients")
    void addDoctorsPatients(){
        Optional<Doctor> optionalDoctor = doctorRepository.findById(1L);
        Optional<Patient> optionalPatient = patientRepository.findById(1L);
        Patient patient = optionalPatient.get();
        Doctor doctor = optionalDoctor.get();
        List<Patient> patientList = patientRepository.findAll();
        List<Doctor> doctorList = doctorRepository.findAll();
        doctor.setPatients(patientList);
        patient.setDoctors(doctorList);
        doctorRepository.save(doctor);
        patientRepository.save(patient);
    }
}
