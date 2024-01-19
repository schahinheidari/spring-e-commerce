package fr.tln.univ.service;

import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Command;

import java.time.LocalDate;
import java.util.List;

public interface CommandService {
    Command save(CommandDto commandDto, String token);

    Command getById(Integer id);

    List<Command> getAll();

    void deleteById(Integer id);

    Command cancelById(Integer Id, String token);

    Command update(CommandDto Command, String token);

    List<Command> getAllByDate(LocalDate date);
}
