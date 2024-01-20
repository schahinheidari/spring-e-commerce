package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.CartDto;
import fr.tln.univ.model.dto.CommandDto;
import fr.tln.univ.model.dto.ProductDto;
import fr.tln.univ.model.entities.Cart;
import fr.tln.univ.model.entities.Command;
import fr.tln.univ.model.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CartMapper {
    public abstract CartDto mapCartToCartDto(Cart cart);
    public abstract Cart mapCartDtoToCart(CartDto cartDto);
}


