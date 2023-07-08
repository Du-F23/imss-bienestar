package edu.mx.utvt.web.model.entities;

import java.util.List;

import edu.mx.utvt.web.model.enums.RouteMedication;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "tc_medicines", indexes = {
		@Index(columnList = "name")
})
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String administration;
	
	@Enumerated(EnumType.ORDINAL)
	private RouteMedication routeMedication;
	
	@ManyToMany(mappedBy = "medicines")
	private List<Diagnosis> diagnoses;

}
