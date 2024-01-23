package fr.tln.univ.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Getter
@Setter
public class InvoiceDto {
    @NotNull(message = "Invoice Number cannot be null")
    private int totalAmount;
    @Pattern(regexp = "[0-9]{2}-[0-9]{2}-[0-9]{4}")
    private Date invoiceDate;
}
