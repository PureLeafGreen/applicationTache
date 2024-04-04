package ca.christopher.applicationtache.repositories;

import ca.christopher.applicationtache.modeles.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {
}
