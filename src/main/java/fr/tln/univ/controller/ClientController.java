package fr.tln.univ.controller;

import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.service.ClientServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientServiceImp clientServiceImp;
    @Autowired
    ClientRepository clientRepository;

    //Add Client-------------------------------------
    @PostMapping("/save")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {
        Client addClient = clientServiceImp.addClient(client);
        System.out.println("Client" + client);
        return new ResponseEntity<Client>(addClient, HttpStatus.CREATED);
    }

    //Get the list of Client-----------------------
    @GetMapping("/list")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        List<ClientDto> clientList = clientServiceImp.getAllClients();
        return ResponseEntity.ok().body(clientList);
    }

    //Get the Client by Id............................
    @GetMapping("/find/{id}")
    public ClientDto getClientById(@PathVariable("id") Integer id){
        return clientServiceImp.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client){
        return clientServiceImp.addClient(client);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @RequestHeader("token") String token) {
        Client updatedClient = clientServiceImp.updateClient(client, token);
        return new ResponseEntity<Client>(updatedClient, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteClient(Integer id){
        clientServiceImp.deleteClient(id);
    }

    // Handler to update customer password
    @PutMapping("/update/password")
    public ResponseEntity<SessionDto> updateClientPassword(@Valid @RequestBody ClientDto clientDto, @RequestHeader("token") String token){
        return new ResponseEntity<>(clientServiceImp.updateClientPassword(clientDto, token), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/signup")
    public String postStringUpPage(@ModelAttribute Client client){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(client.getPassword());
        client.setPassword(password);
        client.setEmail(client.getEmail());
        clientRepository.save(client);
        return null;
    }


}
