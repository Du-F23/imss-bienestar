package edu.mx.utvt.web.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mx.utvt.web.model.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
