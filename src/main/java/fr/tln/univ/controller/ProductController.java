package fr.tln.univ.controller;

import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.model.dto.ProductDto;
import fr.tln.univ.model.entities.Product;
import fr.tln.univ.service.ProductService;
import fr.tln.univ.service.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product/v1")
public class ProductController {

    private final ProductServiceImp produitServiceImp;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> add(@RequestHeader("token") String token, @Valid @RequestBody Product product) {
        Product prod = productService.add(token, product);
        return new ResponseEntity<>(prod, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        String res = productService.deleteById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@Valid @RequestBody Product product) {
        Product prod = productService.update(product);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        Product product = productService.getById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> productList = produitServiceImp.getAll();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductDto>> getProductsOfStatus(@PathVariable String status) {
        List<ProductDto> produitList = produitServiceImp.getByStatus(ProductStatus.valueOf(status.toUpperCase()));
        return new ResponseEntity<>(produitList, HttpStatus.OK);
    }

}