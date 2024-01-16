package fr.tln.univ.service;

import fr.tln.univ.exception.CommandeException;
import fr.tln.univ.exception.LoginException;
import fr.tln.univ.model.dto.CommandeDto;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Commande;

import java.time.LocalDate;
import java.util.List;

public interface CommandeService {
    public Commande saveCommande(CommandeDto commandeDto, String token) throws LoginException, CommandeException;

    public Commande getCommandeByCommandeId(Integer CommandeId) throws CommandeException;

    public List<Commande> getAllCommandes() throws CommandeException;

    public Commande cancelCommandeByCommandeId(Integer CommandeId,String token) throws CommandeException;

    public Commande updateCommandeByCommande(CommandeDto Commande,Integer CommandeId,String token) throws CommandeException,LoginException;

    public List<Commande> getAllCommandesByDate(LocalDate date) throws CommandeException;

    public Client getClientByCommandeid(Integer CommandeId) throws CommandeException;
    public void deleteCommande(Integer id) throws CommandeException;

}
