package fr.tln.univ.service;

import fr.tln.univ.enums.ProduitStatus;
import fr.tln.univ.model.dto.ProduitDto;
import fr.tln.univ.model.entities.Produit;

import java.util.List;

public interface ProduitService {

    public Produit addProduit(String token, Produit produit);

    public Produit getProduitById(Integer id);

    public String deleteProduit(Integer id);

    public Produit updateProduit(Produit produit);

    public List<Produit> getAllProduits();

    public List<ProduitDto> getAllProduitsOfAdmin(Integer id);

    public List<ProduitDto> getProduitsOfStatus(ProduitStatus status);

}
