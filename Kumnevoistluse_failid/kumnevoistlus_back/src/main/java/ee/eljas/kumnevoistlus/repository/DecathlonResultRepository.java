package ee.eljas.kumnevoistlus.repository;

import ee.eljas.kumnevoistlus.entity.DecathlonResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecathlonResultRepository extends JpaRepository<DecathlonResult, Long> {
    List<DecathlonResult> findByAthleteId(Long athleteId);
}
