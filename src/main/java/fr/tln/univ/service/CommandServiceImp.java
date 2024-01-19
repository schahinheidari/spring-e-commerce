package fr.tln.univ.service;

import fr.tln.univ.dao.CommandRepository;
import fr.tln.univ.enums.CommandState;
import fr.tln.univ.exception.CommandeException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.mapper.CommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandServiceImp implements CommandService {

    private final CommandRepository commandRepository;
    private final CommandMapper commandMapper;

    public Command add(Command command) {
        return commandRepository.save(command);
    }

    public void deleteById(Integer id) {
        commandRepository.deleteById(id);
    }


    @Override
    public Command save(CommandDto commandDto, String token) throws LoginException, CommandeException {
        Command command = commandMapper.mapCommandDtoToCommand(commandDto);
        return commandRepository.save(command);
    }

    @Override
    public List<Command> getAll() throws CommandeException {
        return commandRepository.findAll();
    }

    @Override
    public Command cancelById(Integer id, String token) throws CommandeException {
        Command command = commandRepository.findById(id).orElse(null);
        if (command != null) {
            if (command.getCommandState() == CommandState.PENDING || command.getCommandState() == CommandState.REJECTED) {
                return commandRepository.save(command);
            } else {
                throw new CommandeException("Invalid session token for customer" + "Kindly Login");
            }
        } else {
            throw new CommandeException("No Orders exists on your account");
        }
    }

    @Override
    public Command update(CommandDto commandDto, Integer id, String token) throws CommandeException, LoginException {
        Command existingCommand = commandRepository.findById(id).orElseThrow(()
                -> new CommandeException("No command exists with given CommandId " + id));
        return commandRepository.save(commandMapper.mapCommandToCommandDto(existingCommand));
    }

    @Override
    public List<Command> getAllByDate(LocalDate date) throws CommandeException {
        List<Command> commandList = commandRepository.findByDate(date);
        if (!commandList.isEmpty())
            return commandList;
        else
            throw new CommandeException("No Orders exists on your account");
    }

    public Command getById(Integer id) {
        return commandRepository.findById(id).orElseThrow(CommandeException::new);
    }
}