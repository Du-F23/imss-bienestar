package edu.mx.utvt.web.model.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "te_doctors", indexes = {
		@Index(columnList = "firstName"),
		@Index(columnList = "lastName"),
		@Index(columnList = "rfc", unique = true),
		@Index(columnList = "cedula", unique = true)
		})
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Size(min = 2, max = 50)
	@NotNull(message = "It mustn't be null")
	@Column(length = 50)
	private String firstName;

	@Size(min = 5, max = 100)
	@NotNull(message = "It mustn't be null")
	@Column(length = 100)
	private String lastName;
	
	@NotNull(message = "It mustn't be null")
	@Column(length = 13, unique = true)
	private String rfc;
	
	@NotNull(message = "It mustn't be null")
	@Column(length = 10, unique = true)
	private String cedula; 
			
	@CreatedDate
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = " DATETIME DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	public Date createdDate;
	
	@LastModifiedDate
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = " DATETIME DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
		
	@ManyToMany(mappedBy = "doctors")
	private List<Patient> patients; 
	
	
	@ManyToOne
	@JoinColumn(name = "speciality_id")
	private Specialty speciality;
		
	@OneToMany(mappedBy = "doctor")
	private List<Appointment> appointments;
}
