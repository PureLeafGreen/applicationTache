package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.GroupeDTO;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.GroupeRepository;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupeService {

    private final GroupeRepository groupeRepository;
    private final UtilisateurRepository utilisateurRepository;

    public GroupeService(GroupeRepository groupeRepository, UtilisateurRepository utilisateurRepository) {
        this.groupeRepository = groupeRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public GroupeDTO saveGroupe(GroupeDTO groupe) {
        Groupe groupe1 = groupe.fromDto();
        return new GroupeDTO(groupeRepository.save(groupe1));
    }

    public GroupeDTO getGroupe(Long id) {
        return new GroupeDTO(groupeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Groupe non trouvé")));
    }

    public GroupeDTO joinGroupe(Long id, Long userId) {
        Groupe groupe = groupeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Groupe non trouvé"));
        Utilisateur utilisateur = utilisateurRepository.findById(userId).orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé"));
        groupe.getUtilisateurs().add(utilisateur);
        utilisateur.setGroupe(groupe);
        return new GroupeDTO(groupeRepository.save(groupe));
    }
}

