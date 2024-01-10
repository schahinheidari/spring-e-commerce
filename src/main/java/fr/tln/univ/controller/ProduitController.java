package fr.tln.univ.controller;

import fr.tln.univ.enums.ProduitStatus;
import fr.tln.univ.model.dto.ProduitDto;
import fr.tln.univ.model.entities.Produit;
import fr.tln.univ.service.ProduitService;
import fr.tln.univ.service.ProduitServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/products")
public class ProduitController {

    ProduitServiceImp produitServiceImp;
    ProduitService produitService;

    @PostMapping
    public ResponseEntity<Produit> addProduit(@RequestHeader("token") String token,@Valid @RequestBody Produit produit){
        Produit prod = produitService.addProduit(token, produit);
        return new ResponseEntity<Produit>(prod, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduit(@PathVariable("id") Integer id){
        String res = produitService.deleteProduit(id);
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@Valid @RequestBody Produit produit){
        Produit prod = produitService.updateProduit(produit);
        return new ResponseEntity<Produit>(prod, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("id") Integer id){
        Produit produit = produitService.getProduitById(id);
        return new ResponseEntity<Produit>(produit, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Produit>> getAllProduits(){
        List<Produit> produitList = produitServiceImp.getAllProduits();
        return new ResponseEntity<List<Produit>>(produitList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ProduitDto>> getAllProduitsOfAdmin(@PathVariable("id") Integer id){
        List<ProduitDto> produitList = produitServiceImp.getAllProduitsOfAdmin(id);
        return new ResponseEntity<List<ProduitDto>>(produitList, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProduitDto>> getProduitsOfStatus(@PathVariable("status") String status){
        List<ProduitDto> produitList = produitServiceImp.getProduitsOfStatus(ProduitStatus.valueOf(status.toUpperCase()));
        return new ResponseEntity<List<ProduitDto>>(produitList, HttpStatus.OK);
    }

}
