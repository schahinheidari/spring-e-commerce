package fr.tln.univ.service;

import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public List<ClientDto> getAllClients(){
        List<Client> clientList = clientRepository.findAll();
        return clientMapper.listClientToListClientDtoMapper(clientList);
    }


    public ClientDto getClientById(Integer id){
        Client client = clientRepository.findById(id).orElse(null);
        return clientMapper.mapClientToClientDto(client);
//        return clientRepository.findById(id).orElse(null);
    }
    public Client createClient(Client client){
        return clientRepository.save(client);
    }
    public void deleteClient(Integer id){
        clientRepository.deleteById(id);
    }
}
