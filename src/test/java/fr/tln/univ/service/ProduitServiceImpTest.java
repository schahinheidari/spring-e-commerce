package fr.tln.univ.service;

import fr.tln.univ.dao.ProduitRepository;
import fr.tln.univ.enums.ProduitStatus;
import fr.tln.univ.exception.ProduitNotFoundException;
import fr.tln.univ.model.dto.ProduitDto;
import fr.tln.univ.model.entities.Produit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProduitServiceImpTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitServiceImp produitServiceImp;

    ArrayList<Produit> produits;
    List<ProduitDto> produitList;
    Produit produit;
    ProduitStatus status;

    @BeforeEach
    public void setUp(){
        produits = new ArrayList<>();
        produits.add(new Produit());
        produits.add(new Produit());

        produitList = new ArrayList<>();
        produitList.add(new ProduitDto());
        produitList.add(new ProduitDto());

        produit = new Produit();


        status = ProduitStatus.AVAILABLE;

    }

    @Test
    void addProduit() {
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit result = produitServiceImp.addProduit("token", produit);
        assertEquals(produit, result);
        produitRepository.save(produit);
    }

    @Test
    void getProduitById_existingId_shouldReturnProduit() throws ProduitNotFoundException {
        int id = 1;
        when(produitRepository.findById(id)).thenReturn(Optional.of(produit));
        Produit result = produitServiceImp.getProduitById(id);
        assertEquals(produit, result);
    }
    @Test
    void getProduitById_nonExistingId_shouldThrowProduitNotFoundException() {
        int id = 1;
        when(produitRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ProduitNotFoundException.class, () -> produitServiceImp.getProduitById(id));
    }
    @Test
    void deleteProduit_existingId_shouldDeleteProduit() throws ProduitNotFoundException {
        int id = 1;
        when(produitRepository.findById(id)).thenReturn(Optional.of(produit));
        String result = produitServiceImp.deleteProduit(id);
        assertEquals("Product deleted from catalog", result);
        produitRepository.delete(produit);
    }
    @Test
    void deleteProduit_nonExistingId_shouldThrowProduitNotFoundException() {
        int id = 1;
        when(produitRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ProduitNotFoundException.class, () -> produitServiceImp.deleteProduit(id));
    }
    @Test
    void updateProduit_existingProduit_shouldReturnUpdatedProduit() throws ProduitNotFoundException {
        when(produitRepository.findById(produit.getId())).thenReturn(Optional.of(produit));
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit result = produitServiceImp.updateProduit(produit);
        assertEquals(produit, result);
    }
    @Test
    void updateProduit_nonExistingProduit_shouldThrowProduitNotFoundException() {
        when(produitRepository.findById(produit.getId())).thenReturn(Optional.empty());
        assertThrows(ProduitNotFoundException.class, () -> produitServiceImp.updateProduit(produit));
    }

    @Test
    void getAllProduits() {
        when(produitRepository.findAll()).thenReturn(produits);
        Assertions.assertEquals(2,produitServiceImp.getAllProduits().size());
    }

    @Test
    void getAllProduitsOfAdmin() {
        int adminId = 1;
        when(produitRepository.getAllProduitsOfAdmin(adminId)).thenReturn(produitList);
        List<ProduitDto> result = produitServiceImp.getAllProduitsOfAdmin(adminId);
        assertEquals(2, result.size());
    }

    @Test
    void createProduit() {
        when(produitRepository.save(produit)).thenReturn(produit);
        Produit result = produitServiceImp.addProduit("token", produit);
        assertEquals(produit, result);
        produitRepository.save(produit);
    }

    @Test
    void getProduitsOfStatus() {
        when(produitRepository.getProduitsOfStatus(status)).thenReturn(produitList);
        List<ProduitDto> result = produitServiceImp.getProduitsOfStatus(status);
        assertEquals(2, result.size());
    }
}