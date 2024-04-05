package ca.christopher.applicationtache.repositories;

import ca.christopher.applicationtache.modeles.Evenement;
import ca.christopher.applicationtache.modeles.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Optional<Evenement> findByDateDebut(String date);

    @Query("SELECT e FROM Evenement e WHERE SUBSTRING(e.dateDebut, 1, 10) = ?1")
    Collection<Evenement> findAllByDateDebutSameDay(String selectedDate);
    @Query("SELECT e FROM Evenement e WHERE e.utilisateur = :utilisateur")
    Collection<Evenement> findAllByUtilisateur(Utilisateur utilisateur);
}
