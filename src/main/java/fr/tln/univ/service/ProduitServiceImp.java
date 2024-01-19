package fr.tln.univ.service;

import fr.tln.univ.dao.ProduitRepository;
import fr.tln.univ.enums.ProduitStatus;
import fr.tln.univ.exception.ProduitNotFoundException;
import fr.tln.univ.model.dto.ProduitDto;
import fr.tln.univ.model.entities.Produit;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProduitServiceImp implements ProduitService {

    private final ProduitRepository produitRepository;
    
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
            return produitRepository.save(produit);
        } else
            throw new ProduitNotFoundException("Product not found with given id");
    }

    @Override
    public List<Produit> getAllProduits(){
        List<Produit> produitList = produitRepository.findAll();
        if (!produitList.isEmpty()) {
            return produitList;
        } else
            throw new ProduitNotFoundException("No products");
    }

    @Override
    public  List<ProduitDto> getProduitsOfStatus(ProduitStatus status) {
        List<ProduitDto> produitList = produitRepository.getProduitsByProduitStatus(status);
        if (!produitList.isEmpty()) {
            return produitList;
        } else
            throw new ProduitNotFoundException("No products found with given status:" + status);
    }



}
