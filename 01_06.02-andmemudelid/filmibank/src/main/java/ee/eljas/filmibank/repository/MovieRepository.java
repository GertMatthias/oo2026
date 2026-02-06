package ee.eljas.filmibank.repository;

import ee.eljas.filmibank.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
