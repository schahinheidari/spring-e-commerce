package fr.tln.univ.service;

import fr.tln.univ.dao.CartRepository;
import fr.tln.univ.dao.ClientRepository;
import fr.tln.univ.dao.ProductRepository;
import fr.tln.univ.exception.NotFoundException;
import fr.tln.univ.model.entities.Cart;
import fr.tln.univ.model.entities.Client;
import fr.tln.univ.model.entities.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;

    @Override
    public String addProductToCart(Integer clientId, Integer quantity, Integer productId) {
        log.info("Adding product to cart. Client ID: {}, Product ID: {}, Quantity: {}", clientId, productId, quantity);
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                if (product.getQuantity() < quantity || quantity <= 0) {
                    throw new NotFoundException("Out of Stock or Invalid Quantity");
                }
                Cart clientCart = client.getCart();
                if (clientCart == null) {
                    clientCart = new Cart();
                }
                List<Product> cartProducts = clientCart.getCartProducts();
                if (cartProducts.stream().anyMatch(p -> p.getId().equals(productId))) {
                    return "Product is already added to the cart";
                }
                Product cartProduct = new Product();
                cartProduct.setId(productId);
                cartProduct.setPhoto(product.getPhoto());
                cartProduct.setName(product.getName());
                cartProduct.setQuantity(quantity);
                cartProduct.setPriceUnit(product.getPriceUnit());
                cartProducts.add(cartProduct);
                clientCart.setCartProducts(cartProducts);
                cartRepository.save(clientCart);
                return "Product added to the cart";
            } else {
                throw new NotFoundException("Product not found");
            }
        } else {
            log.warn("Client not found for ID: {}", clientId);
            throw new NotFoundException("Client not found");
        }
    }

    @Override
    public List<Product> getAllProduct(Integer id) {
        log.info("Getting all products for client ID: {}", id);
        Optional<Client> client = clientRepository.findById(id);
        if(!client.isPresent()) {
            log.warn("Client not found for ID: {}", id);
            throw new NotFoundException("Client not found");
        }
        Cart cart = client.get().getCart();
        if(cart == null) {
            throw new NotFoundException("Please add product to cart first!");
        }
        if(cart.getCartProducts().size() == 0) {
            throw  new NotFoundException("Cart is Empty");
        }
        return cart.getCartProducts();
    }



    @Override
    public String removeProductfromCart(Integer productId, Integer id) {
        log.info("Removing product from cart. Product ID: {}, Client ID: {}", productId, id);
        Client client = clientRepository.findById(id).get();
        Cart cart = client.getCart();
        if(cart == null) {
            throw new NotFoundException("Please first add product to the cart");
        }
        List<Product> products = cart.getCartProducts();
        if(products.size() == 0) {
            throw new NotFoundException("Please first add product to the cart");
        }
        boolean flag = true;
        for(int i =0;i<products.size();i++) {
            if(productId.intValue() == products.get(i).getId().intValue()) {
                products.remove(i);
                flag = false;
                break;
            }
        }
        if(flag) {
            throw new NotFoundException("Product is not added to cart please add the product");
        }
        cart.setCartProducts(products);
        cartRepository.save(cart);
        return "Product removed from the cart successfully";
    }

    @Override
    public Product updateQuantity(Integer productId,Integer quantity, Integer id) {
        log.info("Updating product quantity. Product ID: {}, Client ID: {}, Quantity: {}", productId, id, quantity);
        Client cust = clientRepository.findById(id).get();
        Cart cart = cust.getCart();
        if(cart == null) {
            throw new NotFoundException("Please first add product to the cart");
        }
        List<Product> products = cart.getCartProducts();
        if(products.size() == 0) {
            throw new NotFoundException("Please first add product to the cart");
        }
        Product product = productRepository.findById(productId).get();
        Product prodto = null;
        for(int i =0;i<products.size();i++) {
            if(productId.intValue() == products.get(i).getId().intValue()) {
                if(quantity>product.getQuantity() || quantity == 0) {
                    throw new NotFoundException("product out of Stock");
                }
                products.get(i).setQuantity(quantity);
                prodto = products.get(i);
                System.out.println(prodto);
                break;
            }
        }
        cart.setCartProducts(products);
        cartRepository.save(cart);
        return prodto;
    }
}