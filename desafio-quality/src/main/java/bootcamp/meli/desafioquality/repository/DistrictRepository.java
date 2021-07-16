package bootcamp.meli.desafioquality.repository;

import bootcamp.meli.desafioquality.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}
