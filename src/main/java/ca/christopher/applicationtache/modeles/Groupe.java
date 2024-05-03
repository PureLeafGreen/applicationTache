package ca.christopher.applicationtache.modeles;

import ca.christopher.applicationtache.DTO.GroupeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String nom;
    private String description;
    @Column(unique = true)
    private String code;
    @ManyToMany
    private List<Utilisateur> utilisateurs;
    @ManyToOne
    private Utilisateur admin;
    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tache> taches;

    public Groupe(GroupeDTO groupe) {
        this.id = groupe.getId();
        this.nom = groupe.getNom();
        this.description = groupe.getDescription();
        this.code = groupe.getCode();
        this.utilisateurs = null;
        this.admin = null;
        this.taches = null;
    }

    public Groupe(String nom, String description, String code) {
        this.nom = nom;
        this.description = description;
        this.code = code;
        this.utilisateurs = null;
        this.admin = null;
        this.taches = null;
        this.id = 0;
    }
}
