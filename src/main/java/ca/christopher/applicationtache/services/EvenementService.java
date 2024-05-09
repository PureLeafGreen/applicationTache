package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.EvenementDTO;
import ca.christopher.applicationtache.DTO.EvenementWithUserDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Evenement;
import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.EvenementRepository;
import ca.christopher.applicationtache.repositories.GroupeRepository;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final UtilisateurRepository utilisateurRepository;

    private final GroupeRepository groupeRepository;

    public EvenementService(EvenementRepository evenementRepository, UtilisateurRepository utilisateurRepository, GroupeRepository groupeRepository) {
        this.evenementRepository = evenementRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.groupeRepository = groupeRepository;
    }

    public EvenementDTO saveEvenement(EvenementDTO evenement) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findById(evenement.getUtilisateur()).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));
            Evenement event = evenement.fromDto();
            event.setUtilisateur(utilisateur);
            return new EvenementDTO(evenementRepository.save(event));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un événement");
        }
    }

    public EvenementDTO getEvenement(Long id) {
        return new EvenementDTO(evenementRepository.findById(id).orElseThrow(() -> new IllegalStateException("Événement non trouvé")));
    }

    public EvenementDTO getEvenementByDate(String date) {
        return new EvenementDTO(evenementRepository.findByDateDebut(date).orElseThrow(() -> new IllegalStateException("Événement non trouvé")));
    }

    public List<EvenementDTO> getEvenementsByDate(String date) {
        try {
            String dateStr = date;
            List<EvenementDTO> evenementDTOS = evenementRepository.findAllByDateDebutSameDay(dateStr).stream().map(EvenementDTO::new).toList();
            if (evenementDTOS.isEmpty()) {
                throw new AppException("Aucun événement trouvé", HttpStatusCode.valueOf(404));
            }
            return evenementDTOS;
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de récupérer les événements");
        }
    }

    public void deleteEvenement(Long id) {
        try {
            if (!evenementRepository.existsById(id)) {
                throw new AppException("Événement non trouvé", HttpStatusCode.valueOf(404));
            }
            evenementRepository.deleteById(id);
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de supprimer l'événement");
        }
    }

    public List<EvenementDTO> getAllEvenements() {
        try {
            List<EvenementDTO> evenementDTOS = evenementRepository.findAll().stream().map(EvenementDTO::new).toList();
            if (evenementDTOS.isEmpty()) {
                throw new AppException("Aucun événement trouvé", HttpStatusCode.valueOf(404));
            }
            return evenementDTOS;
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de récupérer les événements");
        }
    }

    public List<EvenementDTO> getAllEvenementsByUser(Long userid) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findById(userid).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));
            List<EvenementDTO> evenementDTOS = evenementRepository.findAllByUtilisateur(utilisateur).stream().map(EvenementDTO::new).toList();
            if (evenementDTOS.isEmpty()) {
                throw new AppException("Aucun événement trouvé", HttpStatusCode.valueOf(404));
            }
            return evenementDTOS;
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de récupérer les événements");
        }
    }

    public EvenementDTO saveEvenementWithGroup(EvenementDTO evenement, Long groupid) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findById(evenement.getUtilisateur()).orElseThrow(() -> new AppException("Utilisateur non trouvé", HttpStatusCode.valueOf(404)));
            Groupe groupe = groupeRepository.findById(groupid).orElseThrow(() -> new AppException("Groupe non trouvé", HttpStatusCode.valueOf(404)));
            Evenement event = evenement.fromDto();
            event.setUtilisateur(utilisateur);
            event.setGroupe(groupe);
            return new EvenementDTO(evenementRepository.save(event));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de créer un événement");
        }
    }

    public List<EvenementWithUserDTO> getAllEvenementsByGroup(Long groupid) {
        try {
            Groupe groupe = groupeRepository.findById(groupid).orElseThrow(() -> new AppException("Groupe non trouvé", HttpStatusCode.valueOf(404)));
            List<EvenementWithUserDTO> evenementDTOS = evenementRepository.findAllByGroupe(groupe).stream().map(EvenementWithUserDTO::new).toList();
            if (evenementDTOS.isEmpty()) {
                throw new AppException("Aucun événement trouvé", HttpStatusCode.valueOf(404));
            }
            return evenementDTOS;
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de récupérer les événements");
        }
    }

    public EvenementDTO getEvenementById(Long id) {
        try {
            return new EvenementDTO(evenementRepository.findById(id).orElseThrow(() -> new AppException("Événement non trouvé", HttpStatusCode.valueOf(404))));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de récupérer l'événement");
        }
    }

    public EvenementDTO updateEvenement(EvenementDTO evenement) {
        try {
            Evenement event = evenementRepository.findById(evenement.getId()).orElseThrow(() -> new AppException("Événement non trouvé", HttpStatusCode.valueOf(404)));
            event.setNom(evenement.getNom());
            event.setDescription(evenement.getDescription());
            event.setDateDebut(evenement.getDateDebut());
            event.setDateFin(evenement.getDateFin());
            return new EvenementDTO(evenementRepository.save(event));
        }
        catch (AppException e) {
            throw new AppException(e.getMessage(), e.getCode());
        }
        catch (Exception e) {
            throw new IllegalStateException("Impossible de mettre à jour l'événement");
        }
    }
}
