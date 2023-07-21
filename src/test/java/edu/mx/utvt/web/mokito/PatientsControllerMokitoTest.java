package edu.mx.utvt.web.mokito;

import edu.mx.utvt.web.controllers.PatientsController;
import edu.mx.utvt.web.model.repositories.PatientRepository;
import edu.mx.utvt.web.services.PatientsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@Rollback(value = false)
@AutoConfigureMockMvc

public class PatientsControllerMokitoTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PatientsService patientsService;

    @Test
    @DisplayName("Obtiene todos lo pacientes")
    @WithMockUser("admin")
    void getAllPatients() throws Exception {
        log.debug("Obtiene todos los pacientes");

        String url = "/pacientes";

        when(patientsService.showAll()).thenReturn(this.patientRepository.findAll());

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Obtiene solo un paciente de acuerdo a un Id")
    @WithMockUser("admin")
    void getOnePatient() throws Exception {
        log.debug("Obtiene solo un paciente por Id");
        Long id = 1L;

        String url = "/pacientes/" + id.toString();

        when(patientsService.showOne(id)).thenReturn(this.patientRepository.findById(id));

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
