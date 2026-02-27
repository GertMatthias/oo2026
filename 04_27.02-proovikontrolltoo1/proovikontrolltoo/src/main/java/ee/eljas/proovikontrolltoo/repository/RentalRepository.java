package ee.eljas.proovikontrolltoo.repository;

import ee.eljas.proovikontrolltoo.entity.Rental;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<@NonNull Rental,@NonNull Long> {
}