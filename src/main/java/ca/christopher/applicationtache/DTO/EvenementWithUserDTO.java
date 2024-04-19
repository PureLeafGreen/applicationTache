package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Evenement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvenementWithUserDTO {
    private long id;
    private String nom;
    private String description;
    private String dateDebut;
    private String dateFin;
    private UtilisateurDTO utilisateur;
    private GroupeDTO groupe;
    private List<TaskDTO> taches;

    public EvenementWithUserDTO(Evenement evenement) {
        this.id = evenement.getId();
        this.nom = evenement.getNom();
        this.description = evenement.getDescription();
        this.dateDebut = evenement.getDateDebut();
        this.dateFin = evenement.getDateFin();
        this.utilisateur = evenement.getUtilisateur() == null ? null : new UtilisateurDTO(evenement.getUtilisateur());
        this.groupe = null;
        this.taches = null;
    }

    public Evenement fromDto() {
        return new Evenement(nom, description, dateDebut, dateFin, null, null, null);
    }

    public Evenement fromDtoWithId() {
        return new Evenement(id, nom, description, dateDebut, dateFin, null, null, null);
    }
}
