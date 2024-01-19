package fr.tln.univ.service;

import fr.tln.univ.exception.CommandeException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Command;

import java.time.LocalDate;
import java.util.List;

public interface CommandService {
    public Command save(CommandDto commandDto, String token) throws LoginException, CommandeException;

    public Command getById(Integer id) throws CommandeException;

    public List<Command> getAll() throws CommandeException;

    public Command cancelById(Integer Id, String token) throws CommandeException;

    public Command update(CommandDto Command, Integer id, String token) throws CommandeException,LoginException;

    public List<Command> getAllByDate(LocalDate date) throws CommandeException;
}