package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
    private long id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;

    public UtilisateurDTO(String nom, String prenom, String phone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
    }

    public UtilisateurDTO(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.phone = utilisateur.getPhone();
        this.email = utilisateur.getEmail();
    }

    public Utilisateur fromDto() {
        return new Utilisateur(nom, prenom, phone, Role.USER, email);
    }
}
