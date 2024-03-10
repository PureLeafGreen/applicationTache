package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.CredentialsDTO;
import ca.christopher.applicationtache.DTO.LoginUserDTO;
import ca.christopher.applicationtache.DTO.RegisterUserDTO;
import ca.christopher.applicationtache.DTO.UtilisateurDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.exceptions.EmailAlreadyExistsException;
import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatusCode;
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

    public LoginUserDTO saveUser(RegisterUserDTO utilisateur) {
        try {
           Optional<Utilisateur> user = utilisateurRepository.findByEmail(utilisateur.getEmail());
           if (user.isPresent()) {
               throw new AppException("Email déjà existant", HttpStatusCode.valueOf(400));
           }
           user = utilisateurRepository.findByPhone(utilisateur.getPhone());
           if (user.isPresent()) {
             throw new AppException("Téléphonne déjà existant", HttpStatusCode.valueOf(400));
           }
           return new LoginUserDTO(utilisateurRepository.save(utilisateur.fromDTO()));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (RuntimeException e) {
            throw new IllegalStateException("Impossible de créer un utilisateur");
        }

    }

    public Optional<UtilisateurDTO> getUser(Long id) {
        return utilisateurRepository.findById(id).map(UtilisateurDTO::new);
    }

    public UtilisateurDTO getUserByEmail(String email) {
        Utilisateur utilisateur =  utilisateurRepository.findByEmail(email).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));

        return new UtilisateurDTO(utilisateur);
    }

    public LoginUserDTO login(CredentialsDTO utilisateur) {
        Utilisateur user = utilisateurRepository.findByEmail(utilisateur.getEmail()).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));
        if (user.getPassword().equals(utilisateur.getPassword())) {
            return new LoginUserDTO(user);
        }
        throw new AppException("Mot de passe ou email incorrect", HttpStatusCode.valueOf(400));
    }

    public Optional<List<Utilisateur>> getAllUtilisateurs() {
        return Optional.of(utilisateurRepository.findAll());
    }

}
