package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.GroupeDTO;
import ca.christopher.applicationtache.services.GroupeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/groupes")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupeController {
    private final GroupeService groupeService;

    public GroupeController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    @PostMapping("/add")
    public ResponseEntity<GroupeDTO> saveGroupe(@RequestBody GroupeDTO groupe) {
        return ResponseEntity.ok(groupeService.saveGroupe(groupe));
    }

    @PostMapping("/join")
    public ResponseEntity<GroupeDTO> joinGroupe(@RequestParam("id") Long id, @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(groupeService.joinGroupe(id, userId));
    }

    @GetMapping("/get")
    public ResponseEntity<GroupeDTO> getGroupe(@RequestParam("id") Long id) {
        return ResponseEntity.ok(groupeService.getGroupe(id));
    }

}