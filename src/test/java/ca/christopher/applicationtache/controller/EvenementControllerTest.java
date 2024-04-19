package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.EvenementDTO;
import ca.christopher.applicationtache.services.EvenementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EvenementController.class)
public class EvenementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvenementService evenementService;

    private EvenementDTO evenementDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        evenementDTO = new EvenementDTO();
        objectMapper = new ObjectMapper();
        when(evenementService.saveEvenement(any(EvenementDTO.class))).thenReturn(evenementDTO);
        when(evenementService.saveEvenementWithGroup(any(EvenementDTO.class), anyLong())).thenReturn(evenementDTO);
        when(evenementService.getEvenementsByDate(any(String.class))).thenReturn(Collections.singletonList(evenementDTO));
        when(evenementService.getAllEvenements()).thenReturn(Collections.singletonList(evenementDTO));
        when(evenementService.getAllEvenementsByUser(anyLong())).thenReturn(Collections.singletonList(evenementDTO));
        when(evenementService.getAllEvenementsByGroup(anyLong())).thenReturn(Collections.singletonList(evenementDTO));
    }

    @Test
    @DisplayName("Should save event and return status OK")
    public void shouldSaveEvent() throws Exception {
        mockMvc.perform(post("/api/evenement/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evenementDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should save event with group and return status OK")
    public void shouldSaveEventWithGroup() throws Exception {
        mockMvc.perform(post("/api/evenement/addWithGroup")
                        .param("groupid", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(evenementDTO)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get events by date and return status OK")
    public void shouldGetEventsByDate() throws Exception {
        mockMvc.perform(get("/api/evenement/getByDate")
                        .param("date", "2022-12-31"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get all events and return status OK")
    public void shouldGetAllEvents() throws Exception {
        mockMvc.perform(get("/api/evenement/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get all events by user and return status OK")
    public void shouldGetAllEventsByUser() throws Exception {
        mockMvc.perform(get("/api/evenement/getAllByUser")
                        .param("userid", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get all events by group and return status OK")
    public void shouldGetAllEventsByGroup() throws Exception {
        mockMvc.perform(get("/api/evenement/getAllByGroup")
                        .param("groupid", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should delete event and return status OK")
    public void shouldDeleteEvent() throws Exception {
        mockMvc.perform(delete("/api/evenement/delete")
                        .param("id", "1"))
                .andExpect(status().isOk());
    }
}