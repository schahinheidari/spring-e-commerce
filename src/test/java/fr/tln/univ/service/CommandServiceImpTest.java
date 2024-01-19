/*
package fr.tln.univ.service;

import fr.tln.univ.dao.CommandeRepository;
import fr.tln.univ.enums.CommandState;
import fr.tln.univ.exception.CommandeException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.mapper.CommandeMapper;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandServiceImpTest {

    @Mock
    private CommandeRepository commandeRepository;
    @Mock
    private CommandeMapper commandeMapper;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private CommandeServiceImp commandeServiceImp;

    Command command;
    Integer id = 1;
    Integer commandeId = 1;
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
    void createCommande() {
        when(commandeRepository.save(command)).thenReturn(command);
        Command result = commandeServiceImp.createCommande(command);
        assertEquals(command, result);
        commandeRepository.save(command);
    }

    @Test
    void deleteCommande() {
        commandeServiceImp.deleteCommande(id);
        commandeRepository.deleteById(id);
    }

    @Test
    void saveCommande() throws LoginException, CommandeException {
        String token = "token";
        when(commandeMapper.mapCommandeDtoToCommande(commandDto)).thenReturn(command);
        when(commandeRepository.save(command)).thenReturn(command);
        Command result = commandeServiceImp.saveCommande(commandDto, token);
        assertEquals(command, result);
        commandeMapper.mapCommandeDtoToCommande(commandDto);
        commandeRepository.save(command);
    }

    @Test
    void getCommandeByCommandeId() throws CommandeException {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(commande));
        when(commandeMapper.mapCommandeToCommandeDto(commande)).thenReturn(commandeDto);
        Commande result = commandeServiceImp.getCommandeByCommandeId(commandeId);
        assertEquals(commandeDto, result);
        commandeRepository.findById(commandeId);
        commandeMapper.mapCommandeToCommandeDto(commande);
    }

    @Test
    void getAllCommandes_commandeListNotEmpty_shouldReturnCommandeList() throws CommandeException {
        when(commandeRepository.findAll()).thenReturn(commandList);
        List<Command> result = commandeServiceImp.getAllCommandes();
        assertEquals(commandList, result);
    }

    @Test
    void getAllCommandes_commandeListEmpty_shouldThrowCommandeException() {
        when(commandeRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(CommandeException.class, () -> commandeServiceImp.getAllCommandes());
    }

    @Test
    void cancelCommandeByCommandeId_pendingCommande_shouldReturnCommande() throws CommandeException {
        command.setCommandState(CommandState.PENDING);
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(command));
        when(commandeRepository.save(command)).thenReturn(command);
        Command result = commandeServiceImp.cancelCommandeByCommandeId(commandeId, "token");
        assertEquals(command, result);
        commandeRepository.findById(commandeId);
        commandeRepository.save(command);
    }

    @Test
    void cancelCommandeByCommandeId_rejectedCommande_shouldReturnCommande() throws CommandeException {
        command.setCommandState(CommandState.REJECTED);
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(command));
        when(commandeRepository.save(command)).thenReturn(command);
        Command result = commandeServiceImp.cancelCommandeByCommandeId(commandeId, "token");
        assertEquals(command, result);
        commandeRepository.findById(commandeId);
        commandeRepository.save(command);
    }

    @Test
    void cancelCommandeByCommandeId_completedCommande_shouldThrowCommandeException() throws CommandeException {
        Command command = new Command();
        command.setCommandState(CommandState.VERIFIED);
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(command));
        assertThrows(CommandeException.class, () -> commandeServiceImp.cancelCommandeByCommandeId(commandeId, "token"));
    }

    @Test
    void cancelCommandeByCommandeId_commandeNotFound_shouldThrowCommandeException() {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());
        assertThrows(CommandeException.class, () -> commandeServiceImp.cancelCommandeByCommandeId(commandeId, "token"));
    }

    @Test
    void updateCommandeByCommande_existingCommande_shouldReturnUpdatedCommande() throws CommandeException, LoginException {
        Command existingCommand = new Command();
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(existingCommand));
        when(commandeMapper.mapCommandeToCommandeDto(existingCommand)).thenReturn(existingCommand);
        when(commandeRepository.save(existingCommand)).thenReturn(existingCommand);
        Command result = commandeServiceImp.updateCommandeByCommande(commandDto, commandeId, "token");
        assertEquals(existingCommand, result);
        commandeRepository.findById(commandeId);
        commandeMapper.mapCommandeToCommandeDto(existingCommand);
        commandeRepository.save(existingCommand);
    }

    @Test
    void updateCommandeByCommande_nonExistingCommande_shouldThrowCommandeException() {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());
        assertThrows(CommandeException.class, () -> commandeServiceImp.updateCommandeByCommande(new CommandDto(), commandeId, "token"));
    }

    @Test
    void getAllCommandesByDate_commandeListNotEmpty_shouldReturnCommandeList() throws CommandeException {
        when(commandeRepository.findByDate(date)).thenReturn(commandList);
        List<Command> result = commandeServiceImp.getAllCommandesByDate(date);
        assertEquals(commandList, result);
        commandeRepository.findByDate(date);
    }

    @Test
    void getAllCommandesByDate_commandeListEmpty_shouldThrowCommandeException() {
        when(commandeRepository.findByDate(date)).thenReturn(Collections.emptyList());
        assertThrows(CommandeException.class, () -> commandeServiceImp.getAllCommandesByDate(date));
    }

    @Test
    void getClientByCommandeid_commandeNotFound_shouldThrowCommandeException() {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class, () -> commandeServiceImp.getClientByCommandeid(commandeId));
    }
    @Test
    void getClientByCommandeid_existingCommande_shouldReturnClient() throws CommandeException {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(commande));
        when(clientService.getClientById(commande.getClient().getId())).thenReturn(client);
        Client result = commandeServiceImp.getClientByCommandeid(commandeId);
        assertEquals(client, result);
        commandeRepository.findById(commandeId);
        clientService.getClientById(commande.getClient().getId());
    }

    @Test
    void getClientByCommandeid_nonExistingCommande_shouldThrowCommandeException() {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class, () -> commandeServiceImp.getClientByCommandeid(commandeId));
    }
}*/
