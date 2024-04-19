package ca.christopher.applicationtache.repositories;

import ca.christopher.applicationtache.modeles.Tache;
import ca.christopher.applicationtache.modeles.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tache, Long> {
    @Query("SELECT t FROM Tache t WHERE t.utilisateur = ?1")
    List<Tache> findByUtilisateur(Utilisateur utilisateur);
}
