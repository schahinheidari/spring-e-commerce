package fr.tln.univ.service;

import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.dao.SessionRepository;
import fr.tln.univ.exception.ConflictException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.exception.NotFoundException;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.entities.UserSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {

    private final ClientRepository clientRepository;
    private final CommandServiceImp commandServiceImp;
    private final LoginLogoutServiceImp loginService;
    private final SessionRepository sessionRepository;

    @Override
    public Client addClient(Client client) {
        log.info("Adding client: {}", client.getEmail());
        Optional<Client> clientOptional = clientRepository.findByEmail(client.getEmail());
        if (clientOptional.isPresent()) {
            log.error("Client already exists with email: {}", client.getEmail());
            throw new ConflictException("Client is exist.");
        }
        return clientRepository.save(client);
    }

    public Client findByEmail(String email) {
        log.info("Finding client by email: {}", email);
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        if (clientOptional.isEmpty()) {
            log.warn("Client not found for email: {}", email);
            throw new NotFoundException("Client not found");
        }
        return clientOptional.get();
    }

    @Override
    public List<Client> getAllClients() {
        log.info("Getting all clients");
        return clientRepository.findAll();
    }
    @Override
    public Page<Client> paging(Pageable pageable) {
        log.info("Performing client paging with parameters: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        try {
            Page<Client> clientPage = clientRepository.findAll(pageable);
            log.info("Admin paging completed. Total elements: {}", clientPage.getTotalElements());
            return clientPage;
        }catch (Exception ex){
            log.error("Error performing client paging", ex);
            throw ex;
        }
    }

    @Override
    public Client getClientById(Integer id) {
        log.info("Getting client by ID: {}", id);
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()){
            log.warn("Client not found for ID: {}", id);
            throw new NotFoundException("Client not found.");
        }
        return clientOptional.get();
    }

    @Override
    public void deleteClient(Integer id) {
        log.info("Deleting admin by ID: {}", id);
        try {
            getClientById(id);
            clientRepository.deleteById(id);
            log.info("Client delete:{}", id);
        }catch (Exception ex){
            log.error("Error deleting client: {}", id, ex);
            throw ex;
        }
    }

    @Override
    public Client updateClient(Client client, String token) {
        if (!token.contains("client")) {
            log.error("Invalid session token for client");
            throw new LoginException("Invalid session token for client");
        }
        loginService.checkTokenStatus(token);
        try {
            clientRepository.findById(client.getId()).orElseThrow(()
                    -> new NotFoundException("Client not found for this Id: " + client.getId()));
            Client updateCli = clientRepository.save(client);
            log.info("Client update: {}", updateCli);
            return updateCli;
        } catch (NotFoundException ex) {
            log.error("Admin not found for ID: {}", client.getId());
            throw ex;
        } catch (Exception ex) {
            log.error("Error updating admin: {}", client.getId(), ex);
            throw ex;
        }
    }

/*    @Override
    public SessionDto updateClientPassword(Client client, String token) {
        if (!token.contains("client")) {
            throw new LoginException("Invalid session token for client");
        }
        loginService.checkTokenStatus(token);
        UserSession user = sessionRepository.findByToken(token).get();
        Optional<Client> opt = clientRepository.findById(user.getUserId());
        if (opt.isEmpty())
            throw new NotFoundException("Client does not exist");
        Client existingClient = opt.get();

        existingClient.setPassword(client.getPassword());
        clientRepository.save(existingClient);
        SessionDto session = new SessionDto();
        session.setToken(token);
        loginService.logoutClient(session);
        session.setMessage("Updated password and logged out. Login again with new password");
        return session;
    }*/

    @Override
    public Client getClientByCommandId(Integer commandId) {
        log.info("Getting client by command ID: {}", commandId);

        Command command = commandServiceImp.getById(commandId);
        Client client = getClientById(command.getClient().getId());
        log.info("Client retrieved: {}", client.getId());
        return client;
    }
}
