package fr.tln.univ.dao;

import fr.tln.univ.model.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface CommandeRepository extends JpaRepository<Commande, Integer> {
//    List<Commande> findByDate(LocalDate date);
}
