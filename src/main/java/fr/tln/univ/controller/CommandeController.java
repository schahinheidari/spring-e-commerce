package fr.tln.univ.controller;

import fr.tln.univ.dao.CommandeRepository;
import fr.tln.univ.enums.CommandeState;
import fr.tln.univ.exception.CommandeException;
import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Commande;
import fr.tln.univ.model.mapper.CommandeMapper;
import fr.tln.univ.service.ClientServiceImp;
import fr.tln.univ.service.CommandeService;
import fr.tln.univ.service.CommandeServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    private CommandeServiceImp commandeService;
    
    @Autowired
    private CommandeMapper commandeMapper;
    @Autowired
    private ClientServiceImp clientServiceImp;

    /*    public ResponseEntity<List<CommandeDto>> getAllCommande(){
            List<CommandeDto> commandeList = commandeService.getAllCommande();
            return ResponseEntity.ok().body(commandeList);
        }*/
    @PostMapping("/save")
    public ResponseEntity<Commande> addTheNewCommande(@Valid @RequestBody CommandeDto commandeDto, @RequestHeader("token") String token) {
        Commande saveCommande = commandeService.saveCommande(commandeDto, token);
        return new ResponseEntity<Commande>(saveCommande, HttpStatus.CREATED);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Commande> getCommandeByCommandeId(@PathVariable Integer id) {
        Commande commande = this.commandeService.getCommandeByCommandeId(id);
        return ResponseEntity.ok(commandeMapper.mapCommandeToCommandeDto(commande));
    }
    @GetMapping("/list")
    public List<Commande> getAllCommande() {
        List<Commande> listOfAllCommande = commandeService.getAllCommandes();
        return listOfAllCommande;
    }

    @DeleteMapping("/{id}")
    public Commande cancelCommandeByCommandeId(@PathVariable("id") Integer commandeId,@RequestHeader("token") String token){
        return commandeService.cancelCommandeByCommandeId(commandeId,token);
    }
    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Integer id, @Valid @RequestBody CommandeDto commandeDto, @RequestHeader("token") String token) {
        return commandeService.updateCommandeByCommande(commandeDto, id, token);
    }
    @GetMapping("/list/{date}")
    public List<Commande> getAllCommandeByDate(@PathVariable LocalDate date) {
        List<Commande> listOfAllCommande = commandeService.getAllCommandesByDate(date);
        return listOfAllCommande;
    }
    @GetMapping("/client/{id}")
    public Client getClientByCommandeid(@PathVariable Integer id) {
        Client client = this.commandeService.getClientByCommandeid(id);
        return client;
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(Integer id) {
        commandeService.deleteCommande(id);
    }




}
