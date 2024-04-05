package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserDTO {
    private Long id;
    private String email;
    private String prenom;
    private String nom;
    private String phone;
    private String token;
    private Long groupe;


    public LoginUserDTO(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.email = utilisateur.getEmail();
        this.prenom = utilisateur.getPrenom();
        this.nom = utilisateur.getNom();
        this.phone = utilisateur.getPhone();
        this.groupe = utilisateur.getGroupe() != null ? utilisateur.getGroupe().getId() : null;
    }

    public LoginUserDTO(UtilisateurDTO utilisateurDTO) {
        this.id = utilisateurDTO.getId();
        this.email = utilisateurDTO.getEmail();
        this.prenom = utilisateurDTO.getPrenom();
        this.nom = utilisateurDTO.getNom();
        this.phone = utilisateurDTO.getPhone();
    }
}
