package ee.eljas.veebipood.repository;

import ee.eljas.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository  extends JpaRepository<Person,Long> {
    Person findByEmail(String email);
}
