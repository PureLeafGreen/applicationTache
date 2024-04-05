package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.GroupeDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.GroupeRepository;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupeService {

    private final GroupeRepository groupeRepository;
    private final UtilisateurRepository utilisateurRepository;

    public GroupeService(GroupeRepository groupeRepository, UtilisateurRepository utilisateurRepository) {
        this.groupeRepository = groupeRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public GroupeDTO saveGroupe(GroupeDTO groupe) {
        try {
            Groupe groupe1 = groupe.fromDto();
            if (groupe.getUtilisateurs().isEmpty()){
                throw new AppException("Un groupe doit avoir au moins un utilisateur", HttpStatusCode.valueOf(400));
            }
            Utilisateur user = utilisateurRepository.findById(groupe.getUtilisateurs().get(0)).orElseThrow(() -> new AppException("Utilisateur non trouvé" , HttpStatusCode.valueOf(404)));
            user.setGroupe(groupe1);
            groupe1.setUtilisateurs(List.of(user));
            return new GroupeDTO(groupeRepository.save(groupe1));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de créer un groupe");
        }

    }

    public GroupeDTO getGroupe(Long id) {
        return new GroupeDTO(groupeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Groupe non trouvé")));
    }

    public GroupeDTO joinGroupe(String code, Long userId) {
        try {
            Groupe groupe = groupeRepository.findByCode(code).orElseThrow(() -> new AppException("Groupe non trouvé", HttpStatusCode.valueOf(404)));
            Utilisateur utilisateur = utilisateurRepository.findById(userId).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));
            List <Utilisateur> utilisateurs = groupe.getUtilisateurs();
            if (utilisateurs.contains(utilisateur)) {
                throw new AppException("Utilisateur déjà dans le groupe", HttpStatusCode.valueOf(400));
            }
            else {
                utilisateurs.add(utilisateur);
            }
            groupe.setUtilisateurs(utilisateurs);
            utilisateur.setGroupe(groupe);
            return new GroupeDTO(groupeRepository.save(groupe));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de rejoindre le groupe");
        }
    }
}

