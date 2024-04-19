package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Groupe;
import ca.christopher.applicationtache.modeles.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupeDTO {
    private long id;
    private String nom;
    private String description;
    private String code;
    private List<Long> utilisateurs;
    private Long admin;
    private List<Long> taches;

    public GroupeDTO(Groupe groupe) {
        this.id = groupe.getId();
        this.nom = groupe.getNom();
        this.description = groupe.getDescription();
        this.code = groupe.getCode();
        this.utilisateurs = groupe.getUtilisateurs() == null ? null : groupe.getUtilisateurs().stream().map(Utilisateur::getId).toList();
        this.admin = null;
        this.taches = null;
    }

    public Groupe fromDto() {
        return new Groupe(nom, description, code);
    }
}
