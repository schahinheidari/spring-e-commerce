package fr.tln.univ.service;

import fr.tln.univ.dao.ProductRepository;
import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.exception.ProduitNotFoundException;
import fr.tln.univ.model.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product add(String token, Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Integer id) throws ProduitNotFoundException {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent())
            throw new ProduitNotFoundException("Product not found with given id");
        return opt.get();
    }

    @Override
    public void deleteById(Integer id) throws ProduitNotFoundException {
        getById(id);
        productRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public Product update(Product product) throws ProduitNotFoundException {
        getById(product.getId());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getByStatus(ProductStatus status) {
        List<Product> produitList = productRepository.getByStatus(status);
        if (!produitList.isEmpty())
            throw new ProduitNotFoundException("No products found with given status:" + status);
        return produitList;
    }
}