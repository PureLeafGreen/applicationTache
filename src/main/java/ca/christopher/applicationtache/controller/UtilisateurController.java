package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.UtilisateurDTO;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping(path = "/all")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Optional<List<Utilisateur>>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }



    @GetMapping(path = "/get")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Optional<UtilisateurDTO>> getUtilisateur(@RequestParam Long id) {
        return ResponseEntity.ok(utilisateurService.getUser(id));
    }



}
