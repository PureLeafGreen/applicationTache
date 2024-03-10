package ca.christopher.applicationtache.DTO;

import ca.christopher.applicationtache.modeles.Role;
import ca.christopher.applicationtache.modeles.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {
    private long id;
    private String nom;
    private String prenom;
    private String phone;
    private String email;
    private String password;

    public RegisterUserDTO(String nom, String prenom, String phone, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public RegisterUserDTO(Utilisateur utilisateur) {
        this.id = utilisateur.getId();
        this.nom = utilisateur.getNom();
        this.prenom = utilisateur.getPrenom();
        this.phone = utilisateur.getPhone();
        this.email = utilisateur.getEmail();
        this.password = utilisateur.getPassword();
    }

    public Utilisateur fromDTO() {
        return new Utilisateur(nom, prenom, phone, Role.USER, email, password);
    }
}
