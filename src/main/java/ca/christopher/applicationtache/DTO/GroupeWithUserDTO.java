package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Groupe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupeWithUserDTO {
    private long id;
    private String nom;
    private String description;
    private String code;
    private Long admin;
    private Long taches;
    private List<UtilisateurDTO> utilisateurs;

    public GroupeWithUserDTO(Groupe groupe) {
        this.id = groupe.getId();
        this.nom = groupe.getNom();
        this.description = groupe.getDescription();
        this.code = groupe.getCode();
        this.utilisateurs = groupe.getUtilisateurs().stream().map(UtilisateurDTO::new).toList();
        this.admin = null;
        this.taches = null;
    }

    public Groupe fromDto() {
        return new Groupe(nom, description, code);
    }
}
