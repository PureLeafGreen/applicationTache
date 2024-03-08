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

    @PostMapping(path = "/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UtilisateurDTO> addUtilisateur(@RequestBody UtilisateurDTO utilisateur) {
        return (utilisateurService.saveUser(utilisateur).map(ResponseEntity::ok)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(path = "/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Optional<UtilisateurDTO>> getUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUser(id));
    }

    @PostMapping(path = "/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UtilisateurDTO> getUtilisateur(@RequestBody UtilisateurDTO utilisateur) {
        return (utilisateurService.getUserByEmailAndPassword(utilisateur.getEmail(), utilisateur.getPassword()).map(ResponseEntity::ok)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
