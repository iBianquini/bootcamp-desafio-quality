package bootcamp.meli.desafioquality.repository;

import bootcamp.meli.desafioquality.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
