package fr.tln.univ.dao;

import fr.tln.univ.model.dto.AdminDto;
import fr.tln.univ.model.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);

}
