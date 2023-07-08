package edu.mx.utvt.web.model.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import edu.mx.utvt.web.model.enums.UserStatus;
import jakarta.validation.constraints.Email;
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
@Table(name = "te_patients", indexes = {
		@Index(columnList = "firstName"),
		@Index(columnList = "lastName"),
		@Index(columnList = "email")
		})
public class Patient {
	
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
	private Date birthDate;

	@NotNull(message = "It mustn't be null")
	@Column(length = 3)
	private String bloodType;

	@NotNull(message = "It mustn't be null")
	@Column(length = 50, unique = true)
	@Email(regexp = ".+[@].+[\\.].+")
	private String email;
	
	@Enumerated(EnumType.ORDINAL)
	private UserStatus userStatus;
	
	@NotNull(message = "It mustn't be null")
	@Column(length = 13, unique = true)
	private String rfc;
	
	@CreatedDate
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = " DATETIME DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	public Date createdDate;
	
	@LastModifiedDate
	@Column(nullable = false, insertable = false, updatable = false, columnDefinition = " DATETIME DEFAULT NOW()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	@ManyToMany
	@JoinTable(name = "tr_doctors_patients", joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "doctor_id"))
	private List<Doctor> doctors;
	
	@OneToMany(mappedBy = "patient")
	private List<Appointment> appointments;
}
