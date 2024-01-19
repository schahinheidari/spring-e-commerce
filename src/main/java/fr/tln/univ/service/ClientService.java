package fr.tln.univ.service;

import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);

    Client updateClient(Client client, String token);

    void deleteClient(Integer id);

    Client getClientById(Integer id);

    List<Client> getAllClients();

    Client getClientByCommandId(Integer id);

    Page<Client> paging(Pageable pageable);
}
