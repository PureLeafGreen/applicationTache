package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

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

    public Optional<Utilisateur> saveUser(Utilisateur utilisateur) {
        try {
            return Optional.of(utilisateurRepository.save(utilisateur));
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un utilisateur");

        }
    }

    public Optional<Utilisateur> getUser(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Optional<Utilisateur> getUserByEmail(String email) {
        return Optional.ofNullable(utilisateurRepository.findByEmail(email));
    }
}
