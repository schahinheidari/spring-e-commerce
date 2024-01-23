package fr.tln.univ.dao;

import fr.tln.univ.model.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findById(Integer id);
}
