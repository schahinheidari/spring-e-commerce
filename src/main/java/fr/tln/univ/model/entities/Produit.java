package fr.tln.univ.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
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

/*
    @OneToMany(mappedBy = "produit")
    private List<Commande> commande;
*/

    }
