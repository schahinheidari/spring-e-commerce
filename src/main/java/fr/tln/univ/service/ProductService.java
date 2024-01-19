package fr.tln.univ.service;

import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.model.dto.ProductDto;
import fr.tln.univ.model.entities.Product;

import java.util.List;

public interface ProductService {

    public Product add(String token, Product product);

    public Product getById(Integer id);

    public void deleteById(Integer id);

    public Product update(Product product);

    public List<Product> getAll();

    public List<Product> getByStatus(ProductStatus status);
}
