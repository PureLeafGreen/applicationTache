package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.GroupeDTO;
import ca.christopher.applicationtache.DTO.TaskDTO;
import ca.christopher.applicationtache.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tasks")
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @GetMapping("/getAllByUser")
    public ResponseEntity<List<TaskDTO>> getAllByUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(taskService.getAllByUser(userId));
    }
}
