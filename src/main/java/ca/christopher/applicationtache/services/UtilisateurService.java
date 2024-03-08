package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.LoginUserDTO;
import ca.christopher.applicationtache.DTO.UtilisateurDTO;
import ca.christopher.applicationtache.exceptions.EmailAlreadyExistsException;
import ca.christopher.applicationtache.exceptions.UserAlreadyExistsException;
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
            return utilisateurRepository.save(new Utilisateur(nom, prenom, telephone,Role.USER, email, motDePasse));
        } catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un utilisateur");
        }
    }

    public Optional<UtilisateurDTO> saveUser(UtilisateurDTO utilisateur) {
        try {
            Utilisateur user = utilisateur.fromDTO();
//            if (utilisateurRepository.findByEmail(user.getEmail()).isPresent()) {
//                throw new EmailAlreadyExistsException("Email already exists");
//            }
            return Optional.of(new UtilisateurDTO(utilisateurRepository.save(user)));
        }
        catch (EmailAlreadyExistsException e) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un utilisateur");
        }

    }

    public Optional<UtilisateurDTO> getUser(Long id) {
        return utilisateurRepository.findById(id).map(UtilisateurDTO::new);
    }

    public UtilisateurDTO getUserByEmail(String email) {
        Utilisateur utilisateur =  utilisateurRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé"));

        return new UtilisateurDTO(utilisateur);
    }

    public Optional<UtilisateurDTO> getUserByEmailAndPassword(String email, String password) {
        return utilisateurRepository.findByEmailAndPassword(email, password).map(UtilisateurDTO::new);
    }

    public Optional<List<Utilisateur>> getAllUtilisateurs() {
        return Optional.of(utilisateurRepository.findAll());
    }

}
