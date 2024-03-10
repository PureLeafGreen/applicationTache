package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUserDTO {
    private Long id;
    private String email;
    private String prenom;
    private String nom;
    private String token;


    public LoginUserDTO(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.email = utilisateur.getEmail();
        this.prenom = utilisateur.getPrenom();
        this.nom = utilisateur.getNom();
    }

    public LoginUserDTO(UtilisateurDTO utilisateurDTO) {
        this.id = utilisateurDTO.getId();
        this.email = utilisateurDTO.getEmail();
        this.prenom = utilisateurDTO.getPrenom();
        this.nom = utilisateurDTO.getNom();
    }
}
