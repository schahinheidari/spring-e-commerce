package fr.tln.univ.model.dto;

import fr.tln.univ.enums.StatusLivraison;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Getter
@Setter
public class DeliveryDto {
    
    @Pattern(regexp = "[0-9]{2}-[0-9]{2}-[0-9]{4}")
    private Date dateLivaisonPrevue;
    
    private StatusLivraison status;
}
