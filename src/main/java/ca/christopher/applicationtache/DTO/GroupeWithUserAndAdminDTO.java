package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Groupe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupeWithUserAndAdminDTO {
    private long id;
    private String nom;
    private String description;
    private String code;
    private UtilisateurDTO admin;
    private Long taches;
    private List<UtilisateurDTO> utilisateurs;

    public GroupeWithUserAndAdminDTO(Groupe groupe) {
        this.id = groupe.getId();
        this.nom = groupe.getNom();
        this.description = groupe.getDescription();
        this.code = groupe.getCode();
        this.utilisateurs = groupe.getUtilisateurs().stream().map(UtilisateurDTO::new).toList();
        this.admin = groupe.getAdmin() == null ? null : new UtilisateurDTO(groupe.getAdmin());
        this.taches = null;
    }

    public Groupe fromDto() {
        return new Groupe(nom, description, code);
    }
}
