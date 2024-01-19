package fr.tln.univ.service;

import fr.tln.univ.dao.CommandRepository;
import fr.tln.univ.enums.CommandState;
import fr.tln.univ.exception.NotFoundException;
import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.mapper.CommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandServiceImpTest {

    @Mock
    private CommandRepository commandRepository;
    @Mock
    private CommandMapper commandMapper;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private CommandServiceImp commandServiceImp;

    Command command;
    Integer id = 1;
    Integer commandId = 1;
    CommandDto commandDto;
    List<Command> commandList;
    LocalDate date;
    Client client;

    @BeforeEach
    void setUp() {
        command = new Command();
        command.setId(1);

        commandDto = new CommandDto();
        commandList = new ArrayList<>();
        commandList.add(new Command());

        date = LocalDate.now();
        client = new Client();
    }

    @Test
    void createCommand() {
        when(commandRepository.save(command)).thenReturn(command);
        Command result = commandServiceImp.add(command);
        assertEquals(command, result);
        commandRepository.save(command);
    }

    @Test
    void deleteCommand() {
        commandServiceImp.deleteById(id);
        commandRepository.deleteById(id);
    }

    @Test
    void saveCommand() {
        String token = "token";
        when(commandMapper.mapCommandDtoToCommand(commandDto)).thenReturn(command);
        when(commandRepository.save(command)).thenReturn(command);
        Command result = commandServiceImp.save(commandDto, token);
        assertEquals(command, result);
        commandMapper.mapCommandDtoToCommand(commandDto);
        commandRepository.save(command);
    }

/*    @Test
    void getCommandeByCommandeId() throws CommandeException {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(commande));
        when(commandeMapper.mapCommandeToCommandeDto(commande)).thenReturn(commandeDto);
        Commande result = commandeServiceImp.getCommandeByCommandeId(commandeId);
        assertEquals(commandeDto, result);
        commandeRepository.findById(commandeId);
        commandeMapper.mapCommandeToCommandeDto(commande);
    }*/

    @Test
    void getAllCommands_commandListNotEmpty_shouldReturnCommandList() {
        when(commandRepository.findAll()).thenReturn(commandList);
        List<Command> result = commandServiceImp.getAll();
        assertEquals(commandList, result);
    }

    @Test
    void getAllCommands_commandListEmpty_shouldThrowCommandException() {
        when(commandRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> commandServiceImp.getAll());
    }

    @Test
    void cancelCommandByCommandId_pendingCommand_shouldReturnCommand() {
        command.setCommandState(CommandState.PENDING);
        when(commandRepository.findById(commandId)).thenReturn(Optional.of(command));
        when(commandRepository.save(command)).thenReturn(command);
        Command result = commandServiceImp.cancelById(commandId, "token");
        assertEquals(command, result);
        commandRepository.findById(commandId);
        commandRepository.save(command);
    }

    @Test
    void cancelCommandByCommandId_rejectedCommand_shouldReturnCommand() {
        command.setCommandState(CommandState.REJECTED);
        when(commandRepository.findById(commandId)).thenReturn(Optional.of(command));
        when(commandRepository.save(command)).thenReturn(command);
        Command result = commandServiceImp.cancelById(commandId, "token");
        assertEquals(command, result);
        commandRepository.findById(commandId);
        commandRepository.save(command);
    }

    @Test
    void cancelCommandByCommandId_completedCommand_shouldThrowCommandException() {
        Command command = new Command();
        command.setCommandState(CommandState.VERIFIED);
        when(commandRepository.findById(commandId)).thenReturn(Optional.of(command));
        assertThrows(NotFoundException.class, () -> commandServiceImp.cancelById(commandId, "token"));
    }

    @Test
    void cancelCommandByCommandId_commandNotFound_shouldThrowCommandException() {
        when(commandRepository.findById(commandId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> commandServiceImp.cancelById(commandId, "token"));
    }

    @Test
    void updateCommandByCommand_existingCommand_shouldReturnUpdatedCommand() {
        Command existingCommand = new Command();
        when(commandRepository.findById(commandId)).thenReturn(Optional.of(existingCommand));
        //when(commandMapper.mapCommandToCommandDto(existingCommand)).thenReturn(existingCommand);
        when(commandRepository.save(existingCommand)).thenReturn(existingCommand);
        Command result = commandServiceImp.update(commandDto, "token");
        assertEquals(existingCommand, result);
        commandRepository.findById(commandId);
        commandMapper.mapCommandToCommandDto(existingCommand);
        commandRepository.save(existingCommand);
    }

    @Test
    void updateCommandByCommand_nonExistingCommand_shouldThrowCommandException() {
        when(commandRepository.findById(commandId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> commandServiceImp.update(new CommandDto(), "token"));
    }

    @Test
    void getAllCommandsByDate_commandListNotEmpty_shouldReturnCommandList() {
        when(commandRepository.findByDate(date)).thenReturn(commandList);
        List<Command> result = commandServiceImp.getAllByDate(date);
        assertEquals(commandList, result);
        commandRepository.findByDate(date);
    }

    @Test
    void getAllCommandesByDate_commandeListEmpty_shouldThrowCommandeException() {
        when(commandRepository.findByDate(date)).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> commandServiceImp.getAllByDate(date));
    }

    @Test
    void getClientByCommandeid_commandeNotFound_shouldThrowCommandeException() {
        when(commandRepository.findById(commandId)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class, () -> commandServiceImp.getById(commandId));
    }
/*    @Test
    void getClientByCommandeid_existingCommande_shouldReturnClient() throws CommandeException {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(commande));
        when(clientService.getClientById(commande.getClient().getId())).thenReturn(client);
        Client result = commandeServiceImp.getClientByCommandeid(commandeId);
        assertEquals(client, result);
        commandeRepository.findById(commandeId);
        clientService.getClientById(commande.getClient().getId());
    }*/

    @Test
    void getClientByCommandeid_nonExistingCommande_shouldThrowCommandeException() {
        when(commandRepository.findById(commandId)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class, () -> commandServiceImp.getById(commandId));
    }
}