package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.GroupeDTO;
import ca.christopher.applicationtache.DTO.TaskDTO;
import ca.christopher.applicationtache.DTO.TaskWithUserDTO;
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

    @PostMapping("/addWithGroup")
    public ResponseEntity<TaskDTO> saveTaskWithGroup(@RequestBody TaskDTO task, @RequestParam("groupId") Long groupId) {
        return ResponseEntity.ok(taskService.saveTaskWithGroup(task, groupId));
    }

    @GetMapping("/getAllByUser")
    public ResponseEntity<List<TaskDTO>> getAllByUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(taskService.getAllByUser(userId));
    }

    @GetMapping("/getAllByGroup")
    public ResponseEntity<List<TaskWithUserDTO>> getAllByGroup(@RequestParam("groupId") Long groupId) {
        return ResponseEntity.ok(taskService.getAllByGroup(groupId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTask(@RequestParam("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getById")
    public ResponseEntity<TaskDTO> getTask(@RequestParam("id") Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(taskService.updateTask(task));
    }
}
