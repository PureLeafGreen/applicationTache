package ca.christopher.applicationtache.repositories;

import ca.christopher.applicationtache.modeles.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query("SELECT u FROM Utilisateur u WHERE u.email = ?1")
    Optional<Utilisateur> findByEmail(String email);
    @Query("SELECT u FROM Utilisateur u WHERE u.email = ?1 AND u.password = ?2")
    Optional<Utilisateur> findByEmailAndPassword(String email, String password);
}
