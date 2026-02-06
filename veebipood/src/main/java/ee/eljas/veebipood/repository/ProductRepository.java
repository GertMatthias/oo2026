package ee.eljas.veebipood.repository;

import ee.eljas.veebipood.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository - andmebaasiga suhtlemiseks. tema sees on kõik funktsioonid, mida on võimalik andmebaasiga teha.

public interface ProductRepository extends JpaRepository<Product, Long> {
}
