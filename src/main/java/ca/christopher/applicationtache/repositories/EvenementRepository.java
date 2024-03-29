package ca.christopher.applicationtache.repositories;

import ca.christopher.applicationtache.modeles.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}
