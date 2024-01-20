package fr.tln.univ.dao;

import fr.tln.univ.model.entities.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommandRepository extends JpaRepository<Command, Integer> {
    List<Command> findByDate(LocalDate date);
}
