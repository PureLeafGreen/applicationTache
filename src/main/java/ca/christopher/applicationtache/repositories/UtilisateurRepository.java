package ca.christopher.applicationtache.repositories;

import ca.christopher.applicationtache.modeles.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query("SELECT u FROM Utilisateur u WHERE u.email = ?1")
    Utilisateur findByEmail(String email);
}
