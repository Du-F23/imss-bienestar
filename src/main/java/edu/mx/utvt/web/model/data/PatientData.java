package edu.mx.utvt.web.model.data;


import edu.mx.utvt.web.model.entities.Patient;
import edu.mx.utvt.web.model.enums.UserStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public final class PatientData {
    public static Patient newPatient(Patient patient){
        return Patient.builder().id(patient.getId()).firstName(patient.getFirstName()).lastName(patient.getLastName()).birthDate(patient.getBirthDate()).bloodType(patient.getBloodType()).email(patient.getEmail()).userStatus(patient.getUserStatus()).rfc(patient.getRfc()).createdDate(patient.getCreatedDate()).lastModifiedDate(patient.getLastModifiedDate()).build();
    }

    public static Optional<Patient> showOne(Long id) {
        Optional<Patient> optionalPatient = Optional.of(Patient.builder().id(id).firstName("Carolina").lastName("Aguilar").birthDate(new Date()).bloodType("AO+").email("caroagui@gmail.com").userStatus(UserStatus.OPEN).rfc("AGEC040126").createdDate(new Date()).lastModifiedDate(new Date()).build());
        return optionalPatient;
    }

    public static List<Patient> showAll() {
        List<Patient> patientList = null;

        patientList = new ArrayList<>();
        patientList.add(Patient.builder().firstName("Carolina").lastName("Aguilar").birthDate(new Date()).bloodType("AO+").email("caroagui@gmail.com").userStatus(UserStatus.OPEN).rfc("AGEC040126").createdDate(new Date()).lastModifiedDate(new Date()).build());
        patientList.add(Patient.builder().firstName("Fernando").lastName("Duarte").birthDate(new Date()).bloodType("AO+").email("fer@gmail.com").userStatus(UserStatus.OPEN).rfc("DUVF021021HM").createdDate(new Date()).lastModifiedDate(new Date()).build());
        patientList.add(Patient.builder().firstName("Luis").lastName("Enrique").birthDate(new Date()).bloodType("AO+").email("lui@gmail.com").userStatus(UserStatus.OPEN).rfc("GOGLE123432F").createdDate(new Date()).lastModifiedDate(new Date()).build());

        return  patientList;
    }
}
