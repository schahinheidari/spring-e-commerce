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
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService {

    private final ClientRepository clientRepository;
    private final CommandServiceImp commandServiceImp;
    private final LoginLogoutServiceImp loginService;
    private final SessionRepository sessionRepository;

    @Override
    public Client addClient(Client client) {
        Optional<Client> clientOptional = clientRepository.findByEmail(client.getEmail());
        if (clientOptional.isPresent())
            throw new ConflictException("Client is exist.");
        return clientRepository.save(client);
    }

    public Client findByEmail(String email) {
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        if (clientOptional.isEmpty())
            throw new NotFoundException("Client not found");
        return clientOptional.get();
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    @Override
    public Page<Client> paging(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client getClientById(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty())
            throw new NotFoundException("Client not found.");
        return clientOptional.get();
    }

    @Override
    public void deleteClient(Integer id) {
        getClientById(id);
        clientRepository.deleteById(id);
    }

    @Override
    public Client updateClient(Client client, String token) {
        if (!token.contains("client")) {
            throw new LoginException("Invalid session token for client");
        }
        loginService.checkTokenStatus(token);
        clientRepository.findById(client.getId()).orElseThrow(()
                -> new NotFoundException("Client not found for this Id: " + client.getId()));
        return clientRepository.save(client);
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
        Command command = commandServiceImp.getById(commandId);
        return getClientById(command.getClient().getId());
    }
}
