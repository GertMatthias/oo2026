package ee.eljas.veebipood.repository;

import ee.eljas.veebipood.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Repository - andmebaasiga suhtlemiseks. tema sees on kõik funktsioonid, mida on võimalik andmebaasiga teha.

public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAllByCategoryId(Pageable pageable, Long category);
}
