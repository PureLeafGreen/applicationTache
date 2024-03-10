package ca.christopher.applicationtache.controller;

import ca.christopher.applicationtache.DTO.CredentialsDTO;
import ca.christopher.applicationtache.DTO.LoginUserDTO;
import ca.christopher.applicationtache.DTO.RegisterUserDTO;
import ca.christopher.applicationtache.DTO.UtilisateurDTO;
import ca.christopher.applicationtache.config.UserAuthProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ca.christopher.applicationtache.services.UtilisateurService;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final UtilisateurService utilisateurService;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UtilisateurService utilisateurService, UserAuthProvider userAuthProvider) {
        this.utilisateurService = utilisateurService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping(path = "/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<LoginUserDTO> addUtilisateur(@RequestBody RegisterUserDTO utilisateur) {
        LoginUserDTO user = utilisateurService.saveUser(utilisateur);
        user.setToken(userAuthProvider.createToken(user.getEmail()));
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<LoginUserDTO> getUtilisateur(@RequestBody CredentialsDTO utilisateur) {
        LoginUserDTO user = utilisateurService.login(utilisateur);
        user.setToken(userAuthProvider.createToken(user.getEmail()));
        return ResponseEntity.created(URI.create("/users/" + user.getId())).body(user);
    }
}
