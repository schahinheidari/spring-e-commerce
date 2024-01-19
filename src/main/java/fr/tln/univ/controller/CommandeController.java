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
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/commande")
public class CommandeController {

    CommandeService commandeService;
    CommandeMapper commandeMapper;

    @PostMapping("/save")
    public ResponseEntity<Commande> addTheNewCommande(@Valid @RequestBody CommandeDto commandeDto, @RequestHeader("token") String token) {
        Commande saveCommande = commandeService.saveCommande(commandeDto, token);
        return new ResponseEntity<>(saveCommande, HttpStatus.CREATED);
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

    @PutMapping("/{id}")
    public Commande updateCommande(@PathVariable Integer id, @Valid @RequestBody CommandeDto commandeDto, @RequestHeader("token") String token) {
        return commandeService.updateCommandeByCommande(commandeDto, id, token);
    }
    @GetMapping("/list/{date}")
    public List<Commande> getAllCommandeByDate(@PathVariable LocalDate date) {
        return commandeService.getAllCommandesByDate(date);
    }
    @GetMapping("/client/{id}")
    public Client getClientByCommandeid(@PathVariable Integer id) {
        return this.commandeService.getClientByCommandeid(id);
    }

/*
    @DeleteMapping("/{id}")
    public void deleteCommande(Integer id) {
        commandeServiceImp.deleteCommande(id);
    }
*/




}
