package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Tache;
import ca.christopher.applicationtache.modeles.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
        private long id;
        private String nom;
        private String description;
        private String dateDebut;
        private String dateFin;
        private String couleur;
        private TaskStatus type;
        private Long groupe;
        private Long utilisateur;
        private Long evenement;

        public TaskDTO(Tache tache) {
            this.id = tache.getId();
            this.nom = tache.getNom();
            this.description = tache.getDescription();
            this.dateDebut = tache.getDateDebut();
            this.dateFin = tache.getDateFin();
            this.couleur = tache.getCouleur();
            this.type = tache.getType();
            this.groupe = tache.getGroupe() == null ? null : tache.getGroupe().getId();
            this.utilisateur = tache.getUtilisateur() == null ? null : tache.getUtilisateur().getId();
            this.evenement = tache.getEvenement() == null ? null : tache.getEvenement().getId();
        }
        public Tache fromDto() {
            return new Tache(nom, description, dateDebut, dateFin, couleur, type, null, null, null);
        }
}
