package ca.christopher.applicationtache.modeles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String description;
    private String dateDebut;
    private String dateFin;
    @ManyToOne
    private Utilisateur utilisateur;
    @OneToOne
    private Groupe groupe;
    @OneToMany(mappedBy = "evenement",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tache> taches;

    public Evenement(String nom, String description, String dateDebut, String dateFin, Utilisateur utilisateur, Groupe groupe, List<Tache> taches) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.utilisateur = utilisateur;
        this.groupe = groupe;
        this.taches = taches;
    }
}
