package edu.mx.utvt.web.model.entities;

import java.util.List;


import edu.mx.utvt.web.model.enums.MedicalSpecialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tc_specialties", indexes = {
		@Index(columnList = "medicalSpecialty", unique = true)
})
public class Specialty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private MedicalSpecialty medicalSpecialty;
	
	@OneToMany(mappedBy = "speciality")
	private List<Doctor> doctors;

	
}
