package ee.eljas.proovikontrolltoo.repository;

import ee.eljas.proovikontrolltoo.entity.Film;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<@NonNull Film,@NonNull Long> {

    // SELECT * FROM film WHERE days =
    List<Film> findByDays(Integer days);
}