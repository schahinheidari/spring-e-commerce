package fr.tln.univ.service;

import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.model.entities.Product;

import java.util.List;

public interface ProductService {

    Product add(String token, Product product);

    Product getById(Integer id);

    void deleteById(Integer id);

    Product update(Product product);

    List<Product> getAll();

    List<Product> getByStatus(ProductStatus status);
}
