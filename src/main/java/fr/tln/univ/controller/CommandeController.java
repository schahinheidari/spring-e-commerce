package fr.tln.univ.controller;

import fr.tln.univ.dao.CommandeRepository;
import fr.tln.univ.model.dto.ClientDto;
import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Commande;
import fr.tln.univ.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    CommandeService commandeService;

    @Autowired
    CommandeRepository commandeRepository;

/*    public ResponseEntity<List<CommandeDto>> getAllCommande(){
        List<CommandeDto> commandeList = commandeService.getAllCommande();
        return ResponseEntity.ok().body(commandeList);
    }*/

    @GetMapping("/{id}")
    public CommandeDto getClientById(@PathVariable Integer id){
        return commandeService.getCommandeById(id);
    }

    @PostMapping
    public Commande createClient(@RequestBody Commande commande){
        return commandeService.createCommande(commande);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(Integer id){
        commandeService.deleteCommande(id);
    }

}
