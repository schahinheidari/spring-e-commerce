package fr.tln.univ.service;

import fr.tln.univ.dao.ProductRepository;
import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.exception.NotFoundException;
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
    public Product getById(Integer id) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent())
            throw new NotFoundException("Product not found with given id");
        return opt.get();
    }

    @Override
    public void deleteById(Integer id) {
        getById(id);
        productRepository.deleteById(Long.valueOf(id));
    }

    @Override
    public Product update(Product product) {
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
            throw new NotFoundException("No products found with given status:" + status);
        return produitList;
    }
}
