package edu.mx.utvt.web.model.controller;

import edu.mx.utvt.web.model.repositories.PatientRepository;
import edu.mx.utvt.web.services.PatientsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

public class PatientsRouteTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PatientsService patientsService;


    @Test
    @WithMockUser("admin")
    void testPatients() throws Exception {
        log.debug("Prueba a la ruta /api/patients");
        String url = "/api/patients";

        when(patientsService.showAll()).thenReturn(this.patientRepository.findAll());

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
