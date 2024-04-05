package ca.christopher.applicationtache.repositories;

import ca.christopher.applicationtache.modeles.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
    @Query("SELECT g FROM Groupe g WHERE g.code = ?1")
    Optional<Groupe> findByCode(String code);
}
