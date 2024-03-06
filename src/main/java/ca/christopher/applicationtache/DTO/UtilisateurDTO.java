package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Utilisateur;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UtilisateurDTO {
    private long id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;
    private String password;

    public UtilisateurDTO(String nom, String prenom, String phone, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Utilisateur fromDTO() {
        return new Utilisateur(nom, prenom, phone, Role.USER, email, password);
    }
}
