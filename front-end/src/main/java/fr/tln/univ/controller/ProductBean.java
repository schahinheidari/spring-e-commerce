package fr.tln.univ.controller;

import fr.tln.univ.config.RestCaller;
import fr.tln.univ.model.ProductDto;
import org.springframework.http.HttpMethod;


import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

// ProductBean.java
@ViewScoped
@Named("productBean")
public class ProductBean implements Serializable {

    private Integer code;
    private int priceUnit;
    private String name;
    private String description;
    private int quantity;
    private List<ProductDto> products;
//    private boolean showCreateForm;
    private Long productIdToSearch;
    private ProductDto searchedProduct;

    // Add your getters and setters


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public int getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(int priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void createProduct() {

    }

    public ProductDto getById(Long id) {
        return null;
    }

    public List<ProductDto> getAll() {
        return null;
    }

    public void updateProduct(ProductDto productDto) {

    }

    public void deleteProduct(ProductDto productDto) {

    }


    public Long getProductIdToSearch() {
        return productIdToSearch;
    }

    public void setProductIdToSearch(Long productIdToSearch) {
        this.productIdToSearch = productIdToSearch;
    }

    public ProductDto getSearchedProduct() {
        return searchedProduct;
    }

    public void setSearchedProduct(ProductDto searchedProduct) {
        this.searchedProduct = searchedProduct;
    }

    public String searchProductById(Long productIdToSearch) {
        System.out.println("Searching for product with ID: " + productIdToSearch);
        searchedProduct = getById(productIdToSearch);
        return null;
    }


    public void add() {
        ProductDto productDto = new ProductDto();
        productDto.setCode(this.code);
        productDto.setPriceUnite(this.priceUnit);
        productDto.setName(this.name);
        productDto.setName(this.description);
        productDto.setQuantity(this.quantity);

        RestCaller restCaller = new RestCaller();
        restCaller.call("api/products/v1", HttpMethod.POST, productDto, ProductDto.class);

        products = getAll();
    }

}

