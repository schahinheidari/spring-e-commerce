package fr.tln.univ.model.dto;

import fr.tln.univ.model.entities.Product;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Getter
@Setter
public class CartDto {

    private Integer id;

    private List<Product> cartProducts;
}
