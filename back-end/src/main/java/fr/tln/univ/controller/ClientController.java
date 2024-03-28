package fr.tln.univ.controller;

import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Product;
import fr.tln.univ.model.mapper.ClientMapper;
import fr.tln.univ.service.CartServiceImpl;
import fr.tln.univ.service.ClientServiceImp;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client/v1")
@Api(tags = "Information Api")
@RequiredArgsConstructor
public class ClientController {

    private final ClientServiceImp clientServiceImp;
    private final ClientMapper clientMapper;
    private final CartServiceImpl cartService;

    @PostMapping("/save")
    public ResponseEntity<ClientDto> add(@Valid @RequestBody Client client) {
        Client client1 = clientServiceImp.addClient(client);
        return new ResponseEntity<>(clientMapper.mapClientToClientDto(client1), HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ClientDto> getById(@PathVariable("id") Integer id) {
        Client client = clientServiceImp.getClientById(id);
        return ResponseEntity.ok(clientMapper.mapClientToClientDto(client));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientDto>> getAll() {
        List<Client> clientList = clientServiceImp.getAllClients();
        return ResponseEntity.ok(clientMapper.listClientToListClientDtoMapper(clientList));
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> update(@RequestBody Client client
            , @RequestHeader("token") String token) {
        Client updatedClient = clientServiceImp.updateClient(client, token);
        return ResponseEntity.ok(clientMapper.mapClientToClientDto(updatedClient));
    }

/*    // Handler to update customer password
    @PutMapping("/update/password")
    public ResponseEntity<SessionDto> updateClientPassword(@Valid @RequestBody ClientDto clientDto
            , @RequestHeader("token") String token) {
        return new ResponseEntity<>(clientServiceImp.updateClientPassword(clientDto, token)
                , HttpStatus.ACCEPTED);
    }*/

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        clientServiceImp.deleteClient(id);
    }

    @PostMapping(value = "/signup")
    public String postStringUpPage(@ModelAttribute Client client) {
       // BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password =""; //bCryptPasswordEncoder.encode(client.getPassword());
        client.setPassword(password);
        client.setEmail(client.getEmail());
        clientServiceImp.addClient(client);
        return null;
    }

    @GetMapping("/paging/{page}/{size}")
    public Page<Client> paging(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return clientServiceImp.paging(pageable);
    }

    @GetMapping("/find/command/{id}")
    public ResponseEntity<ClientDto> getByCommandId(@PathVariable Integer id) {
        Client client = clientServiceImp.getClientByCommandId(id);
        return ResponseEntity.ok().body(clientMapper.mapClientToClientDto(client));
    }

    @GetMapping("/cart/{clientId}/{quantity}/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable Integer clientId, @PathVariable Integer quantity, @PathVariable Integer productId ){
        String mess = cartService.addProductToCart(clientId,quantity, productId);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }
    @GetMapping("/updatingQuantity/{productId}/{quantity}/{clientId}")
    public ResponseEntity<Product> updateQuantityOfProduct(@PathVariable Integer productId,@PathVariable Integer quantity,@PathVariable Integer clientId){
        Product productdto = cartService.updateQuantity(productId, quantity, clientId);
        return new  ResponseEntity<>(productdto,HttpStatus.OK);
    }

    @DeleteMapping("/removeProductFromCart/{productId}/{clientId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Integer productId,@PathVariable Integer clientId){
        String mess = cartService.removeProductfromCart(productId, clientId);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

}
