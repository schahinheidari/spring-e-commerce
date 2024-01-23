package fr.tln.univ.dao;

import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.enums.StatusLivraison;
import fr.tln.univ.model.entities.Delivery;
import fr.tln.univ.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryDao extends JpaRepository<Delivery, Integer> {
    List<Delivery> getByStatus(StatusLivraison status);

}
