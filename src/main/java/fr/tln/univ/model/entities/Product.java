package fr.tln.univ.model.entities;

import fr.tln.univ.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Product", indexes = {
        @Index(name = "idx_product_quantity", columnList = "QUANTITY")
})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    public static final String CURRENCY = "EUR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CODE", nullable = false)
    private Integer code;

    @Column(name = "price")
    private int priceUnite;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUIT_STATUS")
    private ProductStatus status;


}
