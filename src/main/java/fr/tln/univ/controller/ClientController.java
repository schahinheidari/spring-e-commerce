package fr.tln.univ.controller;

import fr.tln.univ.entities.Client;
import fr.tln.univ.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/getallclient")
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/id")
    public Client getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(Client client){
        return clientService.createClient(client);
    }

    @DeleteMapping("/id")
    public void deleteClient(Integer id){
        clientService.deleteClient(id);
    }

}
