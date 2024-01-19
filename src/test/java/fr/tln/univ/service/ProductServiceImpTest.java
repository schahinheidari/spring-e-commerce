package fr.tln.univ.service;

import fr.tln.univ.dao.ProductRepository;
import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.exception.ConflictException;
import fr.tln.univ.exception.NotFoundException;
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
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImp produitServiceImp;

    ArrayList<Product> products;
    List<ProductDto> productList;
    Product product;
    ProductStatus status;

    @BeforeEach
    public void setUp(){
        products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        productList = new ArrayList<>();
        productList.add(new ProductDto());
        productList.add(new ProductDto());

        product = new Product();


        status = ProductStatus.AVAILABLE;

    }

    @Test
    void addProduit() {
        when(productRepository.save(product)).thenReturn(product);
        Product result = produitServiceImp.add("token", product);
        assertEquals(product, result);
        productRepository.save(product);
    }

    @Test
    void getProduitById_existingId_shouldReturnProduit(){
        int id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        Product result = produitServiceImp.getById(id);
        assertEquals(product, result);
    }
    @Test
    void getProduitById_nonExistingId_shouldThrowProduitNotFoundException() {
        int id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> produitServiceImp.getById(id));
    }
/*    @Test
    void deleteProduit_existingId_shouldDeleteProduit(){
        int id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        String result = produitServiceImp.deleteById(id);
        assertEquals("Product deleted from catalog", result);
        productRepository.delete(product);
    }*/
    @Test
    void deleteProduit_nonExistingId_shouldThrowProduitNotFoundException() {
        int id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> produitServiceImp.deleteById(id));
    }
    @Test
    void updateProduit_existingProduit_shouldReturnUpdatedProduit(){
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        Product result = produitServiceImp.update(product);
        assertEquals(product, result);
    }
    @Test
    void updateProduit_nonExistingProduit_shouldThrowProduitNotFoundException() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> produitServiceImp.update(product));
    }

    @Test
    void getAllProduits() {
        when(productRepository.findAll()).thenReturn(products);
        Assertions.assertEquals(2,produitServiceImp.getAll().size());
    }

    @Test
    void createProduit() {
        when(productRepository.save(product)).thenReturn(product);
        Product result = produitServiceImp.add("token", product);
        assertEquals(product, result);
        productRepository.save(product);
    }

/*    @Test
    void getProduitsOfStatus() {
        when(productRepository.getByStatus(status)).thenReturn(productList);
        List<Product> result = produitServiceImp.getByStatus(status);
        assertEquals(2, result.size());
    }*/
}