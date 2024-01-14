package fr.tln.univ.service;

import fr.tln.univ.dao.CommandeRepository;
import fr.tln.univ.enums.CommandeState;
import fr.tln.univ.exception.CommandeException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Commande;
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
class CommandeServiceImpTest {

    @Mock
    private CommandeRepository commandeRepository;
    @Mock
    private CommandeMapper commandeMapper;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private CommandeServiceImp commandeServiceImp;

    Commande commande;
    Integer id = 1;
    Integer commandeId = 1;
    CommandeDto commandeDto;
    List<Commande> commandeList;
    LocalDate date;
    Client client;

    @BeforeEach
    void setUp() {
        commande = new Commande();
        commande.setId(1);

        commandeDto = new CommandeDto();
        commandeList = new ArrayList<>();
        commandeList.add(new Commande());

        date = LocalDate.now();
        client = new Client();
    }

    @Test
    void createCommande() {
        when(commandeRepository.save(commande)).thenReturn(commande);
        Commande result = commandeServiceImp.createCommande(commande);
        assertEquals(commande, result);
        commandeRepository.save(commande);
    }

    @Test
    void deleteCommande() {
        commandeServiceImp.deleteCommande(id);
        commandeRepository.deleteById(id);
    }

    @Test
    void saveCommande() throws LoginException, CommandeException {
        String token = "token";
        when(commandeMapper.mapCommandeDtoToCommande(commandeDto)).thenReturn(commande);
        when(commandeRepository.save(commande)).thenReturn(commande);
        Commande result = commandeServiceImp.saveCommande(commandeDto, token);
        assertEquals(commande, result);
        commandeMapper.mapCommandeDtoToCommande(commandeDto);
        commandeRepository.save(commande);
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
    void getAllCommandes_commandeListNotEmpty_shouldReturnCommandeList() throws CommandeException {
        when(commandeRepository.findAll()).thenReturn(commandeList);
        List<Commande> result = commandeServiceImp.getAllCommandes();
        assertEquals(commandeList, result);
    }

    @Test
    void getAllCommandes_commandeListEmpty_shouldThrowCommandeException() {
        when(commandeRepository.findAll()).thenReturn(Collections.emptyList());
        assertThrows(CommandeException.class, () -> commandeServiceImp.getAllCommandes());
    }

    @Test
    void cancelCommandeByCommandeId_pendingCommande_shouldReturnCommande() throws CommandeException {
        commande.setCommandeState(CommandeState.PENDING);
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(commande));
        when(commandeRepository.save(commande)).thenReturn(commande);
        Commande result = commandeServiceImp.cancelCommandeByCommandeId(commandeId, "token");
        assertEquals(commande, result);
        commandeRepository.findById(commandeId);
        commandeRepository.save(commande);
    }

    @Test
    void cancelCommandeByCommandeId_rejectedCommande_shouldReturnCommande() throws CommandeException {
        commande.setCommandeState(CommandeState.REJECTED);
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(commande));
        when(commandeRepository.save(commande)).thenReturn(commande);
        Commande result = commandeServiceImp.cancelCommandeByCommandeId(commandeId, "token");
        assertEquals(commande, result);
        commandeRepository.findById(commandeId);
        commandeRepository.save(commande);
    }

    @Test
    void cancelCommandeByCommandeId_completedCommande_shouldThrowCommandeException() throws CommandeException {
        Commande commande = new Commande();
        commande.setCommandeState(CommandeState.VERIFIED);
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(commande));
        assertThrows(CommandeException.class, () -> commandeServiceImp.cancelCommandeByCommandeId(commandeId, "token"));
    }

    @Test
    void cancelCommandeByCommandeId_commandeNotFound_shouldThrowCommandeException() {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());
        assertThrows(CommandeException.class, () -> commandeServiceImp.cancelCommandeByCommandeId(commandeId, "token"));
    }

    @Test
    void updateCommandeByCommande_existingCommande_shouldReturnUpdatedCommande() throws CommandeException, LoginException {
        Commande existingCommande = new Commande();
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.of(existingCommande));
        when(commandeMapper.mapCommandeToCommandeDto(existingCommande)).thenReturn(existingCommande);
        when(commandeRepository.save(existingCommande)).thenReturn(existingCommande);
        Commande result = commandeServiceImp.updateCommandeByCommande(commandeDto, commandeId, "token");
        assertEquals(existingCommande, result);
        commandeRepository.findById(commandeId);
        commandeMapper.mapCommandeToCommandeDto(existingCommande);
        commandeRepository.save(existingCommande);
    }

    @Test
    void updateCommandeByCommande_nonExistingCommande_shouldThrowCommandeException() {
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());
        assertThrows(CommandeException.class, () -> commandeServiceImp.updateCommandeByCommande(new CommandeDto(), commandeId, "token"));
    }

    @Test
    void getAllCommandesByDate_commandeListNotEmpty_shouldReturnCommandeList() throws CommandeException {
        when(commandeRepository.findByDate(date)).thenReturn(commandeList);
        List<Commande> result = commandeServiceImp.getAllCommandesByDate(date);
        assertEquals(commandeList, result);
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
        when(commandeRepository.findById(commandeId)).thenReturn(Optional.empty());
        assertThrows(NullPointerException.class, () -> commandeServiceImp.getClientByCommandeid(commandeId));
    }
}