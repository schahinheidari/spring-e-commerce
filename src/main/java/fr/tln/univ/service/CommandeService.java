package fr.tln.univ.service;

import fr.tln.univ.dao.CommandeRepository;
import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Commande;
import fr.tln.univ.model.mapper.CommandeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandeMapper commandeMapper;



    public CommandeDto getCommandeById(Integer id){
        Commande commande = commandeRepository.findById(id).orElse(null);
        return commandeMapper.mapCommandeToCommandeDto(commande);
    }

    public Commande createCommande(Commande commande){
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Integer id){
        commandeRepository.deleteById(id);
    }


}
