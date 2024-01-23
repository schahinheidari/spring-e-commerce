package fr.tln.univ.model.entities;

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
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "[0-9]{2}-[0-9]{2}-[0-9]{4}")
    @Temporal(TemporalType.DATE)
    private Date invoiceDate;

    @NotNull(message = "Total Amount cannot be null")
    private int totalAmount;
}
