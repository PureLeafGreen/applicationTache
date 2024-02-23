package ca.christopher.applicationtache.modeles;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.*;

import java.util.List;

@Data
@Entity
@DiscriminatorColumn(name = "ADMIN")
@NoArgsConstructor
public class Admin extends Utilisateur{

    public Admin( String nom, String prenom, String phone, Role role, String email, String password) {
        super(nom, prenom, phone, role, email, password);
    }

    public Admin(String nom, String prenom, String phone, String email, String password) {
        super(nom, prenom, phone, Role.ADMIN, email, password);
    }

    public Admin(String nom, String prenom, String phone, String email) {
        super(nom, prenom, phone, Role.ADMIN, email);
    }
}
