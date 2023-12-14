package fr.tln.univ.service;

import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    public Client getClientById(Integer id){
        return clientRepository.findById(id).orElse(null);
    }
    public Client createClient(Client client){
        return clientRepository.save(client);
    }
    public void deleteClient(Integer id){
        clientRepository.deleteById(id);
    }
}
