package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.TaskDTO;
import ca.christopher.applicationtache.services.TaskService;
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

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private TaskDTO task;

    @BeforeEach
    public void setup() {
        task = new TaskDTO();
        task.setId(1L);
        task.setNom("Test Task");
    }

    @Test
    @DisplayName("Should save task and return status OK")
    public void shouldSaveTask() throws Exception {
        when(taskService.saveTask(any())).thenReturn(task);

        mockMvc.perform(post("/api/tasks/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should save task with group and return status OK")
    public void shouldSaveTaskWithGroup() throws Exception {
        when(taskService.saveTaskWithGroup(any(), anyLong())).thenReturn(task);

        mockMvc.perform(post("/api/tasks/addWithGroup")
                        .param("groupId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(task)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get all tasks by user and return status OK")
    public void shouldGetAllTasksByUser() throws Exception {
        when(taskService.getAllByUser(anyLong())).thenReturn(Collections.singletonList(task));

        mockMvc.perform(get("/api/tasks/getAllByUser")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should get all tasks by group and return status OK")
    public void shouldGetAllTasksByGroup() throws Exception {
        when(taskService.getAllByGroup(anyLong())).thenReturn(Collections.singletonList(task));

        mockMvc.perform(get("/api/tasks/getAllByGroup")
                        .param("groupId", "1"))
                .andExpect(status().isOk());
    }
}