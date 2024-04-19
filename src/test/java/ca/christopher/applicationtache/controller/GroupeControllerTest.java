package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.GroupeDTO;
import ca.christopher.applicationtache.services.GroupeService;
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

@WebMvcTest(GroupeController.class)
public class GroupeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupeService groupeService;

    private GroupeDTO groupe;

    @BeforeEach
    public void setup() {
        groupe = new GroupeDTO();
        groupe.setId(1L);
        groupe.setNom("Test Group");
    }

    @Test
    @DisplayName("Should save group and return status OK")
    public void shouldSaveGroup() throws Exception {
        when(groupeService.saveGroupe(any(GroupeDTO.class))).thenReturn(groupe);

        mockMvc.perform(post("/api/groupes/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(groupe)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should join group and return status OK")
    public void shouldJoinGroup() throws Exception {
        when(groupeService.joinGroupe(any(String.class), anyLong())).thenReturn(groupe);

        mockMvc.perform(post("/api/groupes/join")
                        .param("code", "1234")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get group and return status OK")
    public void shouldGetGroup() throws Exception {
        when(groupeService.getGroupe(anyLong())).thenReturn(groupe);

        mockMvc.perform(get("/api/groupes/get")
                        .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get groups and return status OK")
    public void shouldGetGroups() throws Exception {
        when(groupeService.getGroupes(any())).thenReturn(Collections.singletonList(groupe));

        mockMvc.perform(get("/api/groupes/getGroupes")
                        .param("groupesid", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should delete group and return status OK")
    public void shouldDeleteGroup() throws Exception {
        when(groupeService.deleteGroupe(anyLong())).thenReturn(groupe);

        mockMvc.perform(delete("/api/groupes/delete")
                        .param("id", "1"))
                .andExpect(status().isOk());
    }
}