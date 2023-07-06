package edu.mx.utvt.web.model.entities;

import java.util.List;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tc_diseases", indexes = {
		@Index(columnList = "commonName"),
		@Index(columnList = "medicalName")
})
public class Disease {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Length(min = 5, max = 100)
	private String commonName;
	
	@NotBlank
	@Length(min = 5, max = 100)
	private String medicalName;
	
	@NotBlank
	@Length(min = 5, max = 1000)
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "disease")
	private List<Diagnosis> diagnoses;
}
