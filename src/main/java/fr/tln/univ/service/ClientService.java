package fr.tln.univ.service;

import fr.tln.univ.exception.ClientNotFoundException;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Client;

import java.util.List;

public interface ClientService {

    public Client addClient(Client client) throws ClientNotFoundException;

    public Client updateClient(Client client, String token) throws ClientNotFoundException;

    public void deleteClient(Integer id) throws ClientNotFoundException;

    public Client getClientById(Integer id) throws ClientNotFoundException;

    public List<ClientDto> getAllClients() throws ClientNotFoundException;

    public SessionDto updateClientPassword(ClientDto clientDto, String token) throws ClientNotFoundException;

}
