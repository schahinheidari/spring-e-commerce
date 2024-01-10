package fr.tln.univ.service;

import fr.tln.univ.dao.ProduitRepository;
import fr.tln.univ.enums.ProduitStatus;
import fr.tln.univ.exception.ProduitNotFoundException;
import fr.tln.univ.model.dto.ProduitDto;
import fr.tln.univ.model.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitServiceImp implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    
    @Override
    public Produit addProduit(String token, Produit produit) {
        return produitRepository.save(produit);
    }
    
    @Override
    public Produit getProduitById(Integer id) throws ProduitNotFoundException {
        Optional<Produit> opt = produitRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }else
            throw new ProduitNotFoundException("Product not found with given id");
    }    
    
    @Override
    public String deleteProduit(Integer id) throws ProduitNotFoundException {
        Optional<Produit> opt = produitRepository.findById(id);
        if (opt.isPresent()) {
            Produit prod = opt.get();
            System.out.println(prod);
            produitRepository.delete(prod);
            return "Product deleted from catalog";
        } else
            throw new ProduitNotFoundException("Product not found with given id");
    }
    @Override
    public Produit updateProduit(Produit produit) throws ProduitNotFoundException {
        Optional<Produit> opt = produitRepository.findById(produit.getId());
        if (opt.isPresent()) {
            opt.get();
            Produit prod1 = produitRepository.save(produit);
            return prod1;
        } else
            throw new ProduitNotFoundException("Product not found with given id");
    }

    @Override
    public List<Produit> getAllProduits(){
        List<Produit> produitList = produitRepository.findAll();
        if (produitList.size() > 0) {
            return produitList;
        } else
            throw new ProduitNotFoundException("No products");
    }

    @Override
    public List<ProduitDto> getAllProduitsOfAdmin(Integer id){
        List<ProduitDto> produitList = produitRepository.getAllProduitsOfAdmin(id);
        if (produitList.size() > 0) {
            return produitList;
        } else
            throw new ProduitNotFoundException("No products");
    }
    public Produit createProduit(Produit Produit){
        return produitRepository.save(Produit);
    }
    @Override
    public  List<ProduitDto> getProduitsOfStatus(ProduitStatus status) {
        List<ProduitDto> produitList = produitRepository.getProduitsOfStatus(status);
        if (produitList.size() > 0) {
            return produitList;
        } else
            throw new ProduitNotFoundException("No products found with given status:" + status);
    }



}
