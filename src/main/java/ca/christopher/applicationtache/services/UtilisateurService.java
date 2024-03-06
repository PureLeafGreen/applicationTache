package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.UtilisateurDTO;
import ca.christopher.applicationtache.exceptions.EmailAlreadyExistsException;
import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur saveUser(String nom, String prenom, String telephone, String email, String motDePasse) {
        try {
            return utilisateurRepository.save(new Utilisateur(nom, prenom, telephone, Role.USER, email, motDePasse));
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un utilisateur");
        }
    }

    public Optional<Utilisateur> saveUser(UtilisateurDTO utilisateur) {
        try {
            Utilisateur user = utilisateur.fromDTO();
            return Optional.of(utilisateurRepository.save(user));
        }
        catch (EmailAlreadyExistsException e) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un utilisateur");
        }

    }

    public Optional<Utilisateur> getUser(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Optional<Utilisateur> getUserByEmail(String email) {
        return Optional.ofNullable(utilisateurRepository.findByEmail(email));
    }

    public Optional<List<Utilisateur>> getAllUtilisateurs() {
        return Optional.of(utilisateurRepository.findAll());
    }
}
