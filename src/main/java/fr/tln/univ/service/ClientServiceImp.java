package fr.tln.univ.service;

import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.dao.SessionRepository;
import fr.tln.univ.exception.ClientException;
import fr.tln.univ.exception.ClientNotFoundException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.exception.NotFoundException;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Commande;
import fr.tln.univ.model.entities.UserSession;
import fr.tln.univ.model.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ClientServiceImp implements ClientService{


    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    private LoginLogoutServiceImp loginService;
    private SessionRepository sessionRepository;

    @Override
    public Client addClient(Client client){
        Optional<Client> clientOptional = clientRepository.findByEmail(client.getEmail());
        if (clientOptional.isPresent())
            throw new ClientException("Client is exist.");
        return clientRepository.save(client);
    }

    public Client findByEmail(String email){
        Optional<Client> clientOptional = clientRepository.findByEmail(email);
        if (clientOptional.isEmpty())
            throw new NotFoundException("Client not found");
        return clientOptional.get();
    }

    @Override
    public List<ClientDto> getAllClients(){
        List<Client> clientList = clientRepository.findAll();
        return clientMapper.listClientToListClientDtoMapper(clientList);
    }

    @Override
    public ClientDto getClientById(Integer id){
        Client client = clientRepository.findById(id).orElse(null);
        return clientMapper.mapClientToClientDto(client);
//        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteClient(Integer id){
        getClientById(id);
        clientRepository.deleteById(id);
    }

    @Override
    public Client updateClient(Client client, String token) {
        if (!token.contains("client")) {
            throw new LoginException("Invalid session token for client");
        }
        loginService.checkTokenStatus(token);
        clientRepository.findById(client.getId()).orElseThrow(() -> new ClientException("Client not found for this Id: " + client.getId()));
        return clientRepository.save(client);
    }

    @Override
    public SessionDto updateClientPassword(ClientDto clientDto, String token) {
        if(!token.contains("client")) {
            throw new LoginException("Invalid session token for client");
        }
        loginService.checkTokenStatus(token);
        UserSession user = sessionRepository.findByToken(token).get();
        Optional<Client> opt = clientRepository.findById(user.getUserId());
        if(opt.isEmpty())
            throw new ClientNotFoundException("Client does not exist");
        Client existingClient = opt.get();

        existingClient.setPassword(clientDto.getPassword());
        clientRepository.save(existingClient);
        SessionDto session = new SessionDto();
        session.setToken(token);
        loginService.logoutClient(session);
        session.setMessage("Updated password and logged out. Login again with new password");
        return session;
    }


}
