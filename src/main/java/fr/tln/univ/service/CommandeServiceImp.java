package fr.tln.univ.service;

import fr.tln.univ.dao.CommandeRepository;
import fr.tln.univ.enums.CommandeState;
import fr.tln.univ.exception.CommandeException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Commande;
import fr.tln.univ.model.mapper.CommandeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeServiceImp implements CommandeService {
    
    private CommandeRepository commandeRepository;
    private CommandeMapper commandeMapper;
    private ClientService clientService;
    private CommandeService commandeService;

    public Commande createCommande(Commande commande){
        return commandeRepository.save(commande);
    }

    public void deleteCommande(Integer id){
        commandeRepository.deleteById(id);
    }


    @Override
    public Commande saveCommande(CommandeDto commandeDto, String token) throws LoginException, CommandeException {
        Commande commande = commandeMapper.mapCommandeDtoToCommande(commandeDto);
        return commandeRepository.save(commande);
    }

    @Override
    public Commande getCommandeByCommandeId(Integer CommandeId) throws CommandeException {
        Commande commande = commandeRepository.findById(CommandeId).orElse(null);
        return commandeMapper.mapCommandeToCommandeDto(commande);
    }

    @Override
    public List<Commande> getAllCommandes() throws CommandeException {
        // TODO Auto-generated method stub
        List<Commande> commandeList = commandeRepository.findAll();
        if(commandeList.size()>0)
            return commandeList;
        else
            throw new CommandeException("No Orders exists on your account");
    }

    @Override
    public Commande cancelCommandeByCommandeId(Integer commandeId, String token) throws CommandeException {
        Commande commande = commandeRepository.findById(commandeId).orElse(null);
        if(commande!=null) {
            if(commande.getCommandeState()== CommandeState.PENDING || commande.getCommandeState()== CommandeState.REJECTED) {
                return commandeRepository.save(commande);
            }
            else {
                throw new CommandeException("Invalid session token for customer"+"Kindly Login");
            }
        }
        else {
            throw new CommandeException("No Orders exists on your account");
        }
    }

    @Override
    public Commande updateCommandeByCommande(CommandeDto commandeDto, Integer commandeId, String token) throws CommandeException, LoginException {
        Commande existingCommande= commandeRepository.findById(commandeId).orElseThrow(()->new CommandeException("No commande exists with given CommandeId "+ commandeId));
        return commandeRepository.save(commandeMapper.mapCommandeToCommandeDto(existingCommande));
    }

    @Override
    public List<Commande> getAllCommandesByDate(LocalDate date) throws CommandeException {
        List<Commande> commandeListOntheDay = commandeRepository.findByDate(date);
        if(commandeListOntheDay.size()>0)
            return commandeListOntheDay;
        else
            throw new CommandeException("No Orders exists on your account");
    }
    @Override
    public Client getClientByCommandeid(Integer CommandeId) throws CommandeException {
        Commande commande = commandeRepository.findById(CommandeId).orElse(null);
        return clientService.getClientById(commande.getClient().getId());
    }

}
