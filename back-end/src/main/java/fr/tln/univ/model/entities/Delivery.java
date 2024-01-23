package fr.tln.univ.model.entities;

import fr.tln.univ.enums.ProductStatus;
import fr.tln.univ.enums.StatusLivraison;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_LIVRAISON_PREVUE")
    @NotNull(message = "Delivery Date cannot be null")
    @Pattern(regexp = "[0-9]{2}-[0-9]{2}-[0-9]{4}")
    private Date dateLivaisonPrevue;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_LIVRAISON")
    private StatusLivraison status;
}
