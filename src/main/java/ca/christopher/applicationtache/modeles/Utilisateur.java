package ca.christopher.applicationtache.modeles;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "UTILISATEUR")
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    @Pattern(regexp = "^(\\d{3}-\\d{3}-\\d{4})$", message = "Invalid phone number")
    private String phone;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @Column(unique = true)
    @Pattern(regexp = "^(.+)@(.+)$", message = "Invalid email")
    private String email;
    private String password;
    @ManyToOne
    private Groupe groupe;
    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tache> taches;

    public Utilisateur(String nom, String prenom, String phone,Role role, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public Utilisateur(String nom, String prenom, String phone,Role role, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.role = role;
        this.email = email;
    }
}