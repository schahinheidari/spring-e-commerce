package fr.tln.univ.controller;

import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/getallclient")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clientList = clientService.getAllClients();
        return ResponseEntity.ok().body(clientList);
    }

    @GetMapping("/{id}")
    public ClientDto getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientService.createClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(Integer id){
        clientService.deleteClient(id);
    }

/*    @PostMapping(value = "/signup")
    public String postStringUpPage(@ModelAttribute Client client){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(client.getPassword());
        client.setPassword(password);
        client.setEmail(client.getEmail());
        clientRepository.save(client);
        return null;
    }*/

}
