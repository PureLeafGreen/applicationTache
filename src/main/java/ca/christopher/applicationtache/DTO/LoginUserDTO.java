package ca.christopher.applicationtache.DTO;

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

    public LoginUserDTO(UtilisateurDTO utilisateurDTO) {
        this.id = utilisateurDTO.getId();
        this.email = utilisateurDTO.getEmail();
        this.prenom = utilisateurDTO.getPrenom();
        this.nom = utilisateurDTO.getNom();
    }
}
