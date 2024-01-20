package fr.tln.univ.model.entities;


import fr.tln.univ.enums.CommandState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Command implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "CREATED_DATE")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    @NotNull
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUIT_COMMANDE")
    private Product product;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CommandState commandState;
}
