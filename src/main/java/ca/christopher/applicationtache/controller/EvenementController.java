package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.EvenementDTO;
import ca.christopher.applicationtache.services.EvenementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/evenement")
@CrossOrigin(origins = "http://localhost:3000")
public class EvenementController {
    public final EvenementService evenementService;

    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @PostMapping("/add")
    public ResponseEntity<EvenementDTO> saveEvenement(@RequestBody EvenementDTO evenement) {
        return ResponseEntity.ok(evenementService.saveEvenement(evenement));
    }

    @GetMapping("/get")
    public ResponseEntity<EvenementDTO> getEvenement(@RequestBody EvenementDTO evenement) {
        return ResponseEntity.ok(evenementService.getEvenement(evenement.getId()));
    }
}
