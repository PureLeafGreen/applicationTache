package ca.christopher.applicationtache.services;

import ca.christopher.applicationtache.DTO.EvenementDTO;
import ca.christopher.applicationtache.exceptions.AppException;
import ca.christopher.applicationtache.modeles.Evenement;
import ca.christopher.applicationtache.modeles.Utilisateur;
import ca.christopher.applicationtache.repositories.EvenementRepository;
import ca.christopher.applicationtache.repositories.UtilisateurRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final UtilisateurRepository utilisateurRepository;

    public EvenementService(EvenementRepository evenementRepository, UtilisateurRepository utilisateurRepository) {
        this.evenementRepository = evenementRepository;
        this.utilisateurRepository = utilisateurRepository;
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
}