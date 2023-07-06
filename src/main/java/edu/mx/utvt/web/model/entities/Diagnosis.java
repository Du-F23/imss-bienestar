package edu.mx.utvt.web.model.entities;

import java.util.List;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "te_diagnoses")
public class Diagnosis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "diagnosis")
	private Appointment appointment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "disease_id")
	private Disease disease;
	
	@NotBlank
	@Length(min = 5, max = 2000)
	private String comments;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tr_diagnosis_medicines", joinColumns = @JoinColumn(name = "diagnosis_id"), inverseJoinColumns = @JoinColumn(name = "medicine_id"))
	private List<Medicine> medicines;

}
