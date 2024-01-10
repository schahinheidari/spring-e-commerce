package fr.tln.univ.dao;

import fr.tln.univ.enums.ProduitStatus;
import fr.tln.univ.model.dto.ProduitDto;
import fr.tln.univ.model.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findAllByName(String name);
    Produit deleteById(Integer id);
    Optional<Produit> findById(Integer id);

    List<ProduitDto> getAllProduitsOfAdmin(Integer id);

    List<ProduitDto> getProduitsOfStatus(ProduitStatus status);
}
