package fr.tln.univ.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    public static final String CURRENCY = "EUR";

    @Id
    @Column(name = "CODE", nullable = false)
    private Integer code;
    @Column(name = "prix")
    private int prixUnitaire;
    @Column(name = "NAME", nullable = false)
    private String name;
    @JoinColumn(name = "CLIENTID", nullable = false, updatable = false)
    private Client client;

    }
