package fr.tln.univ.controller;

import fr.tln.univ.model.entities.Produit;
import fr.tln.univ.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProduitController {

    @Autowired
    ProduitService produitService;

    @GetMapping("/getallproduct")
    public ResponseEntity<List<Produit>> getAllProducts(){
        List<Produit> produitList = produitService.getAllProduit();
        return ResponseEntity.ok().body(produitList);
    }

    @GetMapping("/{id}")
    public Produit getProductById(@PathVariable Integer id){
        return produitService.getProduitById(id);
    }

    @PostMapping
    public Produit createProduct(Produit produit){
        return produitService.createProduit(produit);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(Integer id){
        produitService.deleteProduit(id);
    }


}
