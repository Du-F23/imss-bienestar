package edu.mx.utvt.web.model.entities;

import java.util.Date;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import edu.mx.utvt.web.model.enums.AppointmentStatus;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "te_appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	@Enumerated(EnumType.ORDINAL)
	private AppointmentStatus appointmentStatus;

	@NotEmpty(message = "It musn't be empty ")
	@Length(min = 5, max = 200)
	private String reason;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@OneToOne
	@JoinColumn(name = "diagnosis_id")
	private Diagnosis diagnosis;

}
