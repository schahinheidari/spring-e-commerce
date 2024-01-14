package fr.tln.univ.controller;

import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.SessionDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.service.ClientServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
@Api(tags = "Information Api")
@RequiredArgsConstructor
public class ClientController {

    ClientServiceImp clientServiceImp;
    ClientRepository clientRepository;

    // Add Client-------------------------------------
    @PostMapping("/save")
/*    @Operation(summary = "Add a new client", description = "Add a new client")
    @ApiResponse(responseCode = "201", description = "Client added successfully",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Client.class))})*/
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {
        Client addClient = clientServiceImp.addClient(client);
        System.out.println("Client" + client);
        return new ResponseEntity<>(addClient, HttpStatus.CREATED);
    }

    // Get the list of Clients-----------------------
    @GetMapping("/list")
/*    @Operation(summary = "Get the list of Clients", description = "Get the list of Clients")
    @ApiResponse(responseCode = "200", description = "OK",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientDto.class))})*/
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clientList = clientServiceImp.getAllClients();
        return ResponseEntity.ok().body(clientList);
    }

    // Get the Client by Id............................
    @GetMapping("/find/{id}")
/*    @Operation(summary = "Get a client by ID", description = "Get a client by ID")
    @ApiResponse(responseCode = "200", description = "OK",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClientDto.class))})*/
    public ClientDto getClientById(@PathVariable("id") Integer id) {
        return clientServiceImp.getClientById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientServiceImp.addClient(client);
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @RequestHeader("token") String token) {
        Client updatedClient = clientServiceImp.updateClient(client, token);
        return new ResponseEntity<>(updatedClient, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteClient(Integer id) {
        clientServiceImp.deleteClient(id);
    }

    // Handler to update customer password
    @PutMapping("/update/password")
    public ResponseEntity<SessionDto> updateClientPassword(@Valid @RequestBody ClientDto clientDto, @RequestHeader("token") String token) {
        return new ResponseEntity<>(clientServiceImp.updateClientPassword(clientDto, token), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/signup")
    public String postStringUpPage(@ModelAttribute Client client) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(client.getPassword());
        client.setPassword(password);
        client.setEmail(client.getEmail());
        clientRepository.save(client);
        return null;
    }
}
