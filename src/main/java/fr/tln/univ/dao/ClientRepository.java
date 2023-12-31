package fr.tln.univ.dao;

import fr.tln.univ.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    boolean findByEmail(String email);

 /*   List<Client> findAllByName(String name);
    boolean findByEmail(String email);*/

}
