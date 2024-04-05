package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.EvenementDTO;
import ca.christopher.applicationtache.services.EvenementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getByDate")
    public ResponseEntity<List<EvenementDTO>> getEvenement(@RequestParam("date") String date) {
        return ResponseEntity.ok(evenementService.getEvenementsByDate(date));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EvenementDTO>> getAllEvenements() {
        return ResponseEntity.ok(evenementService.getAllEvenements());
    }

    @GetMapping("/getAllByUser")
    public ResponseEntity<List<EvenementDTO>> getAllEvenements(@RequestParam("userid") Long userid) {
        return ResponseEntity.ok(evenementService.getAllEvenementsByUser(userid));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteEvenement(@RequestParam("id") Long id) {
        evenementService.deleteEvenement(id);
        return ResponseEntity.ok("Evenement supprimé");
    }


}
