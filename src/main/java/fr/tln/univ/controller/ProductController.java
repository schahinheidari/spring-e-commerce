package fr.tln.univ.controller;

import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.model.dto.ProductDto;
import fr.tln.univ.model.entities.Product;
import fr.tln.univ.model.mapper.ProductMapper;
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

    private final ProductServiceImp productServiceImp;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDto> add(@RequestHeader("token") String token
            , @Valid @RequestBody Product product) {
        Product product1 = productServiceImp.add(token, product);
        return ResponseEntity.ok(productMapper.mapProductToProductDto(product1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Integer id) {
        productServiceImp.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@Valid @RequestBody Product product) {
        Product product1 = productServiceImp.update(product);
        return ResponseEntity.ok(productMapper.mapProductToProductDto(product1));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable Integer id) {
        Product product = productServiceImp.getById(id);
        return ResponseEntity.ok(productMapper.mapProductToProductDto(product));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getAll() {
        List<Product> productList = productServiceImp.getAll();
        return ResponseEntity.ok(productMapper.listProductToListProductDto(productList));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductDto>> getProductsOfStatus(@PathVariable String status) {
        List<Product> productList = productServiceImp.getByStatus(ProductStatus.valueOf(status.toUpperCase()));
        return ResponseEntity.ok(productMapper.listProductToListProductDto(productList));
    }
}
