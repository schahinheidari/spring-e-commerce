package fr.tln.univ.model.dto;

import fr.tln.univ.model.entities.Commande;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto implements Serializable {

    private Integer id;

    @NotNull(message = "Name cannot be null")
    @Pattern.List({
            //@Pattern(regexp = "^[\\p{Alpha} ]*$", message = "Name should contain only alphabets and space", groups = AllLevels.class),
            @Pattern(regexp = "^[\\p{Alpha} ]*$", message = "family should contain only alphabets and space"),
            @Pattern(regexp = "^[^\\s].*$", message = "family should not start with space"),
            @Pattern(regexp = "^.*[^\\s]$", message = "family should not end with space"),
            @Pattern(regexp = "^((?!  ).)*$", message = "family should not contain consecutive spaces"),
            @Pattern(regexp = "^[^a-z].*$", message = "family should not start with a lower case character")
    })
    private String name;

    @NotNull(message = "Name cannot be null")
    @Pattern.List({
            @Pattern(regexp = "^[\\p{Alpha} ]*$", message = "family should contain only alphabets and space"),
            @Pattern(regexp = "^[^\\s].*$", message = "family should not start with space"),
            @Pattern(regexp = "^.*[^\\s]$", message = "family should not end with space"),
            @Pattern(regexp = "^((?!  ).)*$", message = "family should not contain consecutive spaces"),
            @Pattern(regexp = "^[^a-z].*$", message = "family should not start with a lower case character")
    })
    private String family;

    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$", message = "Name should contain only alphabets and space")

    private String email;

    private String password;

    private List<CommandeDto> commandeDtoList;

    // user name
    //@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "User Name should contain only alphabets and space")

}
