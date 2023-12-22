package fr.tln.univ.service;

import fr.tln.univ.dao.ProduitRepository;
import fr.tln.univ.model.entities.Produit;
import fr.tln.univ.model.mapper.ProduitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;


    public List<Produit> getAllProduit(){
        return produitRepository.findAll();
    }
    public Produit getProduitById(Integer id){
        return produitRepository.findById(Long.valueOf(id)).orElse(null);
    }
    public Produit createProduit(Produit Produit){
        return produitRepository.save(Produit);
    }
    public void deleteProduit(Integer id){
        produitRepository.deleteById(Long.valueOf(id));
    }


}
