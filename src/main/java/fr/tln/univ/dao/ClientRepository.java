package fr.tln.univ.dao;

import fr.tln.univ.model.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Page<Client> findAll(Pageable pageable);
    Optional<Client> findByEmail(String email);
}
