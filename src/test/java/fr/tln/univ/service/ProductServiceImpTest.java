/*
package fr.tln.univ.service;

import fr.tln.univ.dao.ProduitRepository;
import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.exception.ProduitNotFoundException;
import fr.tln.univ.model.dto.ProductDto;
import fr.tln.univ.model.entities.Product;
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
class ProductServiceImpTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitServiceImp produitServiceImp;

    ArrayList<Product> products;
    List<ProductDto> produitList;
    Product product;
    ProductStatus status;

    @BeforeEach
    public void setUp(){
        products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        produitList = new ArrayList<>();
        produitList.add(new ProductDto());
        produitList.add(new ProductDto());

        product = new Product();


        status = ProductStatus.AVAILABLE;

    }

    @Test
    void addProduit() {
        when(produitRepository.save(product)).thenReturn(product);
        Product result = produitServiceImp.addProduit("token", product);
        assertEquals(product, result);
        produitRepository.save(product);
    }

    @Test
    void getProduitById_existingId_shouldReturnProduit() throws ProduitNotFoundException {
        int id = 1;
        when(produitRepository.findById(id)).thenReturn(Optional.of(product));
        Product result = produitServiceImp.getProduitById(id);
        assertEquals(product, result);
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
        when(produitRepository.findById(id)).thenReturn(Optional.of(product));
        String result = produitServiceImp.deleteProduit(id);
        assertEquals("Product deleted from catalog", result);
        produitRepository.delete(product);
    }
    @Test
    void deleteProduit_nonExistingId_shouldThrowProduitNotFoundException() {
        int id = 1;
        when(produitRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ProduitNotFoundException.class, () -> produitServiceImp.deleteProduit(id));
    }
    @Test
    void updateProduit_existingProduit_shouldReturnUpdatedProduit() throws ProduitNotFoundException {
        when(produitRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(produitRepository.save(product)).thenReturn(product);
        Product result = produitServiceImp.updateProduit(product);
        assertEquals(product, result);
    }
    @Test
    void updateProduit_nonExistingProduit_shouldThrowProduitNotFoundException() {
        when(produitRepository.findById(product.getId())).thenReturn(Optional.empty());
        assertThrows(ProduitNotFoundException.class, () -> produitServiceImp.updateProduit(product));
    }

    @Test
    void getAllProduits() {
        when(produitRepository.findAll()).thenReturn(products);
        Assertions.assertEquals(2,produitServiceImp.getAllProduits().size());
    }

    @Test
    void createProduit() {
        when(produitRepository.save(product)).thenReturn(product);
        Product result = produitServiceImp.addProduit("token", product);
        assertEquals(product, result);
        produitRepository.save(product);
    }

    @Test
    void getProduitsOfStatus() {
        when(produitRepository.getProduitsByProduitStatus(status)).thenReturn(produitList);
        List<ProductDto> result = produitServiceImp.getProduitsOfStatus(status);
        assertEquals(2, result.size());
    }
}*/
