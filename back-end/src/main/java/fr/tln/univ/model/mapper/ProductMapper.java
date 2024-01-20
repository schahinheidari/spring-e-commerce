package fr.tln.univ.model.mapper;

import fr.tln.univ.model.dto.ProductDto;
import fr.tln.univ.model.entities.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public abstract ProductDto mapProductToProductDto(Product product);
    public abstract List<ProductDto> listProductToListProductDto(List<Product> productList);
}
