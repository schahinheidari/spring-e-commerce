package fr.tln.univ.model.dto;

import fr.tln.univ.model.entities.Commande;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto implements Serializable {

    private Integer id;

    private String name;

    private String family;

    private String email;

    private String password;

    private List<CommandeDto> commandeDtoList;

}
