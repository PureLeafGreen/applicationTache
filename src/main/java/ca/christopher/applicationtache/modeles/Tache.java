package ca.christopher.applicationtache.modeles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tache {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private long id;
    private String nom;
    private String description;
    private String dateDebut;
    private String dateFin;
    private String couleur;
    private String type;
    @ManyToOne
    private Groupe groupe;
    @ManyToOne
    private Utilisateur utilisateur;
}
