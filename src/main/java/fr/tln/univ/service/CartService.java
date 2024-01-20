package fr.tln.univ.service;

import fr.tln.univ.model.entities.Cart;
import fr.tln.univ.model.entities.Product;

import java.util.List;

public interface CartService {
    String addProductToCart(Integer clientId, Integer quantity, Integer productId);
    List<Product> getAllProduct(Integer id);
    String removeProductfromCart(Integer productId, Integer id);
    Product updateQuantity(Integer productId,Integer quantity, Integer id);


}
