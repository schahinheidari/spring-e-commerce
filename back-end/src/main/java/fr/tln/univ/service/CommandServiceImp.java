package fr.tln.univ.service;

import fr.tln.univ.dao.CommandRepository;
import fr.tln.univ.enums.CommandState;
import fr.tln.univ.exception.ConflictException;
import fr.tln.univ.exception.NotFoundException;
import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.mapper.CommandMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandServiceImp implements CommandService {

    private final CommandRepository commandRepository;
    private final CommandMapper commandMapper;

    public Command add(Command command) {
        log.info("Adding command: {}", command);
        return commandRepository.save(command);
    }

    @Override
    public void deleteById(Integer id) {
        log.info("Deleting command by ID: {}", id);
        getById(id);
        commandRepository.deleteById(id);
    }


    @Override
    public Command save(CommandDto commandDto, String token){
        log.info("Saving command: {}", commandDto);
        Command command = commandMapper.mapCommandDtoToCommand(commandDto);
        return commandRepository.save(command);
    }

    @Override
    public List<Command> getAll(){
        log.info("Getting all commands");
        return commandRepository.findAll();
    }

    @Override
    public Command cancelById(Integer id, String token){
        log.info("Canceling command by ID: {}", id);
        Command command = commandRepository.findById(id).orElse(null);
        if (command != null) {
            if (command.getCommandState() == CommandState.PENDING || command.getCommandState() == CommandState.REJECTED) {
                return commandRepository.save(command);
            } else {
                throw new ConflictException("Invalid session token for customer" + "Kindly Login");
            }
        } else {
            throw new ConflictException("No Orders exists on your account");
        }
    }

    @Override
    public Command update(CommandDto commandDto, String token){
        log.info("Updating command: {}", commandDto);
        Command existingCommand = commandRepository.findById(commandDto.getId()).orElseThrow(()
                -> new NotFoundException("No command exists with given CommandId " + commandDto.getId()));
        return commandRepository.save(existingCommand);
    }

    @Override
    public List<Command> getAllByDate(LocalDate date){
        log.info("Getting all commands by date: {}", date);
        List<Command> commandList = commandRepository.findByDate(date);
        if (!commandList.isEmpty())
            return commandList;
        else
            throw new ConflictException("No Orders exists on your account");
    }

    public Command getById(Integer id) {
        log.info("Getting command by ID: {}", id);
        Optional<Command> optionalCommand = commandRepository.findById(id);
        if (optionalCommand.isEmpty())
            throw new NotFoundException("Command not found.");
        return optionalCommand.get();
    }
}
