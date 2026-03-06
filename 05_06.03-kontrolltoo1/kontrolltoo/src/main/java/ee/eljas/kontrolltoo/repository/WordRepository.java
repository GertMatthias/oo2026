package ee.eljas.kontrolltoo.repository;

import ee.eljas.kontrolltoo.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word,Long> {
}
