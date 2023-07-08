package edu.mx.utvt.web.controllers;

import edu.mx.utvt.web.model.entities.Patient;
import edu.mx.utvt.web.model.repositories.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    private final PatientRepository patientRepository;

    public PatientsController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> showAll() {
        return ResponseEntity.ok().body(this.patientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> showOne(@PathVariable("id") Long id){
        return  ResponseEntity.of(this.patientRepository.findById(id));
    }
}
