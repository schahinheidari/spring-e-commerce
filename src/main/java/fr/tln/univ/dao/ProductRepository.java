package fr.tln.univ.dao;

import fr.tln.univ.enums.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Integer id);

    List<Product> getByStatus(ProductStatus status);
}
